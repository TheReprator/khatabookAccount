package reprator.khatabookAccount.accountService.domain.default

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.accountService.AccountInvalidAuthorization
import reprator.khatabookAccount.accountService.AccountInvalidData
import reprator.khatabookAccount.accountService.data.AccountAccessTokenRepository
import reprator.khatabookAccount.accountService.data.AccountAccessTokenResource
import reprator.khatabookAccount.accountService.data.AccountRepository
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory
import reprator.khatabookAccount.accountService.domain.default.validation.PhoneValidator
import reprator.khatabookAccount.accountService.domain.default.validation.PhoneValidatorImpl
import reprator.khatabookAccount.accountService.domain.token.TokenService
import reprator.khatabookAccount.service.JWTAuthenticatedUser

class DefaultAccountResourceFactory(
    override val di: DI
) : DIAware, PhoneValidator by PhoneValidatorImpl, AccountResourceFactory {

    private val tokenService by instance<TokenService>("tokenService")
    private val accountRepository by instance<AccountRepository>("accountRepository")
    private val accountTokenRepository by instance<AccountAccessTokenRepository>("accountAccessTokenRepository")

    override suspend fun create(
        phoneNumber: PhoneNumber,
        isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountResource {

        if (!validatePhoneNumber(phoneNumber))
            throw AccountInvalidData("Phone Number can't be less & greater than 10")

        val organizationId: Int = with(parentId) {
            if (null == this)
                -1
            else {
                val parentOrganizationId = accountRepository.getParentOrganization(this)
                if (!parentOrganizationId) {
                    throw AccountInvalidData("Invalid Organization Id")
                }
                this
            }
        }

        if (accountRepository.isUserExist(phoneNumber, organizationId))
            throw AccountInvalidData("Account already exit with this organization ID")
        else {
            val accountId = accountRepository.saveUser(phoneNumber, isVerified, organizationId)

            val accessToken = tokenService.generateAccessToken(accountId, phoneNumber, organizationId)
            val refreshToken = tokenService.generateRefreshToken(accountId, phoneNumber, organizationId)

            saveUserToken(
                accessToken, refreshToken,
                JWTAuthenticatedUser(accountId, phoneNumber, organizationId.toString())
            )

            return AccountResource(
                accountId, phoneNumber, isVerified, organizationId,
                AccountAccessTokenResource(accessToken, refreshToken)
            )
        }
    }

    override suspend fun refreshToken(
        accessToken: String,
        refreshToken: String,
        authenticatedUser: JWTAuthenticatedUser
    ): AccountAccessTokenResource {

        if (accessToken.isBlank())
            throw AccountInvalidAuthorization("Invalid Token ID")

        if (refreshToken.isBlank())
            throw AccountInvalidAuthorization("Invalid Refresh Token ID")

        accountTokenRepository.updateUserToken(
            authenticatedUser.userId, accessToken, refreshToken
        )

        val newAccessToken = tokenService.generateAccessToken(
            authenticatedUser.userId, authenticatedUser.phoneNumber,
            authenticatedUser.organizationId.toInt()
        )

        val newRefreshToken = tokenService.generateRefreshToken(
            authenticatedUser.userId,
            authenticatedUser.phoneNumber, authenticatedUser.organizationId.toInt()
        )

        saveUserToken(newAccessToken, newRefreshToken, authenticatedUser)

        return AccountAccessTokenResource(newAccessToken, newRefreshToken)
    }

    override suspend fun saveUserToken(
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken,
        authenticatedUser: JWTAuthenticatedUser
    ) {
        accountTokenRepository.saveUserToken(
            authenticatedUser.userId, argAccessToken,
            argRefreshToken
        )
    }

    override suspend fun logout(userId: AccountId, argAccessToken: ModelsAccessToken) {
        accountTokenRepository.disableToken(userId, argAccessToken)
    }
}