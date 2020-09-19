package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.*

interface AccountRepository {
    suspend fun save(argPhoneNumber: PhoneNumber,
                       argIsVerified: VerificationStatus,
                       argParentId: ParentOrganization): AccountId

    suspend fun getParentOrganization(organizationId: ParentOrganization): Boolean

    suspend fun select(argsPhoneNumber: PhoneNumber,
                       organizationId: ParentOrganization): Account
}