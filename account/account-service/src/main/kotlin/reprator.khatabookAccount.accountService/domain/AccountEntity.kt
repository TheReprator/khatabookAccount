package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.*

interface AccountEntity {
    val id: AccountId
    val phoneNumber: PhoneNumber
    val isVerified: VerificationStatus
    val parentId: ParentOrganization

    val accessToken: ModelsAccessToken
    val refreshToken: ModelsAccessToken

    data class DTO(
        override val id: AccountId,
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization,

        override val accessToken: ModelsAccessToken,
        override val refreshToken: ModelsAccessToken
    ) : AccountEntity
}
