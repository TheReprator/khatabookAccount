package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus

data class AccountResource(
        val id: AccountId,
        val phoneNumber: PhoneNumber,
        val isVerified: VerificationStatus,
        val parentId: ParentOrganization
)