package reprator.khatabookAccount.accountService.domain.default

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.erased.instance
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.AccountInvalidData
import reprator.khatabookAccount.accountService.data.AccountRepository
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory

private const val LENGTH_PHONENUMBER = 10

class DefaultAccountResourceFactory(
    override val di: DI
) : DIAware, AccountResourceFactory {

    private val accountRepository by di.instance<AccountRepository>()

    override suspend fun create(
        phoneNumber: PhoneNumber,
        isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountResource {

        phoneNumber.let {
            if (LENGTH_PHONENUMBER > it.length)
                throw AccountInvalidData("Phone Number can't be less than 10")
            else if (LENGTH_PHONENUMBER < it.length)
                throw AccountInvalidData("Phone Number can't be greater than 10")
        }

        with(parentId){
            if(null == this)
                -1
            else
            {
                val parentOrganizationId = accountRepository.getParentOrganization(this)
                if (!parentOrganizationId) {
                    throw AccountInvalidData("Invalid Organization Id")
                }
                parentOrganizationId
            }
        }


        val accountId = accountRepository.save(phoneNumber, isVerified, parentId!!)
        return AccountResource(accountId, phoneNumber, isVerified, parentId)
    }
}