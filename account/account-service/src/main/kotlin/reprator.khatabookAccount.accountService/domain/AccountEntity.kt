package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus

interface AccountEntity {
    val id: AccountId
    val phoneNumber: PhoneNumber
    val isVerified: VerificationStatus
    val parentId: ParentOrganization

    data class DTO(
        override val id: AccountId,
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization
    ) : AccountEntity
}
