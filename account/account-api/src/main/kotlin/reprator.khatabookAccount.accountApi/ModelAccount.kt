package reprator.khatabookAccount.accountApi

typealias PhoneNumber = String
typealias VerificationStatus = Boolean
typealias ParentOrganization = Int
typealias AccountId = Int

/**
 * Represents the information relevant for an account.
 */
interface AccountInfo {
    val phoneNumber: PhoneNumber
    val isVerified: VerificationStatus
    val parentId: ParentOrganization?


    data class DTO(
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization?
    ) : AccountInfo
}

/**
 * Represents the information for a persisted account.
 */
interface Account : AccountInfo {
    val id: AccountId

    val accessTokenInfo: AccessTokenInfo

    data class DTO(
        override val id: AccountId,
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization,

        override val accessTokenInfo: AccessTokenInfo
    ) : Account
}