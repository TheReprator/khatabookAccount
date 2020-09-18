package reprator.khatabookAccount.accountService.domain.default

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.erased.instance
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.data.AccountResource
import reprator.khatabookAccount.accountService.domain.AccountEntity
import reprator.khatabookAccount.accountService.domain.AccountFacade
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory

class DefaultAccountFacade(
    override val di: DI
) : AccountFacade, DIAware {

    private val factory by di.instance<AccountResourceFactory>()

    override suspend fun create(
        phoneNumber: PhoneNumber,
        isVerified: VerificationStatus,
        parentId: ParentOrganization?
    ): AccountEntity {
        val result = factory.create(phoneNumber, isVerified, parentId)
        return result.toAccountEntity()
    }

    private fun AccountResource.toAccountEntity() = AccountEntity.DTO(
        id, phoneNumber,
        isVerified, parentId
    )
}