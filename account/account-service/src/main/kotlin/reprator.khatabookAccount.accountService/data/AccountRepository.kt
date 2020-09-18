package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus

interface AccountRepository {
    suspend fun save(argPhoneNumber: PhoneNumber,
                       argIsVerified: VerificationStatus,
                       argParentId: ParentOrganization): AccountId

    suspend fun getParentOrganization(organizationId: ParentOrganization): Boolean
}