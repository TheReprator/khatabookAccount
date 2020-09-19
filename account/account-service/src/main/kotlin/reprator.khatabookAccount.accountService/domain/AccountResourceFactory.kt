package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.data.AccountResource


interface AccountResourceFactory {
    suspend fun create(
        phoneNumber: PhoneNumber, isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountResource
}