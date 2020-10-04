package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.accountService.data.AccountAccessTokenResource
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.service.JWTAuthenticatedUser


interface AccountResourceFactory {

    suspend fun create(
        phoneNumber: PhoneNumber, isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountResource

    suspend fun refreshToken(
        accessToken: String, refreshToken: String,
        authenticatedUser: JWTAuthenticatedUser
    ): AccountAccessTokenResource

    suspend fun saveUserToken(
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken,
        authenticatedUser: JWTAuthenticatedUser
    )
}