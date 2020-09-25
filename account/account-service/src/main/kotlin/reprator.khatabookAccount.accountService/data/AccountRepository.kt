package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.*

interface AccountRepository {
    suspend fun saveUser(argPhoneNumber: PhoneNumber,
                         argIsVerified: VerificationStatus,
                         argParentId: ParentOrganization): AccountId

    suspend fun getParentOrganization(organizationId: ParentOrganization): Boolean

    suspend fun isUserExist(argsPhoneNumber: PhoneNumber,
                            organizationId: ParentOrganization): Boolean
}