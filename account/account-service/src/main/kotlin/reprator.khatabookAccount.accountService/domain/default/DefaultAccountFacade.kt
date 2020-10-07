package reprator.khatabookAccount.accountService.domain.default

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.data.AccountAccessTokenResource
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.accountService.domain.AccessTokenEntity
import reprator.khatabookAccount.accountService.domain.AccountEntity
import reprator.khatabookAccount.accountService.domain.AccountFacade
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory
import reprator.khatabookAccount.service.JWTAuthenticatedUser

class DefaultAccountFacade(
    override val di: DI
) : AccountFacade, DIAware {

    private val factory by instance<AccountResourceFactory>("accountResourceFactory")

    override suspend fun create(
        phoneNumber: PhoneNumber,
        isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountEntity {
        val result = factory.create(phoneNumber, isVerified, parentId)
        return result.toAccountEntity()
    }

    override suspend fun refreshToken(
        accessToken: String,
        refreshToken: String,
        authenticatedUser: JWTAuthenticatedUser
    ): AccessTokenEntity {
        val result = factory.refreshToken(accessToken, refreshToken, authenticatedUser)
        return result.toAccountAccessTokenEntity()
    }

    override suspend fun logout(userId: AccountId, accessToken: String) {
        factory.logout(userId, accessToken)
    }

    private fun AccountResource.toAccountEntity() = AccountEntity.DTO(
        id, phoneNumber,
        isVerified,
        parentId,
        AccessTokenEntity.DTO(accountAccessTokenResource.accessToken,
            accountAccessTokenResource.refreshToken)
    )

    private fun AccountAccessTokenResource.toAccountAccessTokenEntity() = AccessTokenEntity.DTO(
        accessToken,
        refreshToken
    )
}