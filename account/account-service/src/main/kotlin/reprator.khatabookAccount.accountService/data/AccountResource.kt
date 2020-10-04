package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.*

data class AccountResource(
        val id: AccountId,
        val phoneNumber: PhoneNumber,
        val isVerified: VerificationStatus,
        val parentId: ParentOrganization,

        val accessToken: ModelsAccessToken,
        val refreshToken: ModelsAccessToken
)


data class AccountAccessTokenResource(
        val accessToken: ModelsAccessToken,
        val refreshToken: ModelsAccessToken
)