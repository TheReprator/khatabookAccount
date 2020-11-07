package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.accountService.AccountInvalidData
import reprator.khatabookAccount.service.JWTAuthenticatedUser

interface AccountFacade {

    @Throws(AccountInvalidData::class)
    suspend fun create(
        phoneNumber: PhoneNumber, isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountEntity

    @Throws(AccountInvalidData::class)
    suspend fun refreshToken(
        accessToken: String, refreshToken: String,
        authenticatedUser: JWTAuthenticatedUser
    ): AccessTokenEntity

    @Throws(AccountInvalidData::class)
    suspend fun logout(
        userId: AccountId,
        accessToken: String
    )

    @Throws(AccountInvalidData::class)
    suspend fun isTokenValid(
        userId: AccountId,
        accessToken: ModelsAccessToken
    ): Boolean
}