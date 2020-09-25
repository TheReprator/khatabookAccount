package reprator.khatabookAccount.accountService.domain.default

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.AccountInvalidData
import reprator.khatabookAccount.accountService.data.AccountRepository
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory
import reprator.khatabookAccount.accountService.domain.default.validation.PhoneValidator
import reprator.khatabookAccount.accountService.domain.default.validation.PhoneValidatorImpl

class DefaultAccountResourceFactory(
    override val di: DI
) : DIAware, PhoneValidator by PhoneValidatorImpl, AccountResourceFactory {

    private val accountRepository by instance<AccountRepository>("accountRepository")

    override suspend fun create(
        phoneNumber: PhoneNumber,
        isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountResource {

        if (!validatePhoneNumber(phoneNumber))
            throw AccountInvalidData("Phone Number can't be less & greater than 10")

        val organizationId: Int = with(parentId) {
            if (null == this)
                -1
            else {
                val parentOrganizationId = accountRepository.getParentOrganization(this)
                if (!parentOrganizationId) {
                    throw AccountInvalidData("Invalid Organization Id")
                }
                this
            }
        }

        if (accountRepository.isUserExist(phoneNumber, organizationId))
            throw AccountInvalidData("Account already exit with this organization ID")
        else {
            val accountId = accountRepository.saveUser(phoneNumber, isVerified, organizationId)
            return AccountResource(accountId, phoneNumber, isVerified, organizationId)
        }
    }
}