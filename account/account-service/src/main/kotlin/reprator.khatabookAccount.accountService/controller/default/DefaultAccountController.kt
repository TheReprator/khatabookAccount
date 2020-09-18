package reprator.khatabookAccount.accountService.controller.default

import io.ktor.application.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.kodein.di.ktor.di
import reprator.khatabookAccount.accountApi.Account
import reprator.khatabookAccount.accountApi.AccountInfo
import reprator.khatabookAccount.accountService.controller.AbstractAccountController
import reprator.khatabookAccount.accountService.domain.AccountEntity
import reprator.khatabookAccount.accountService.domain.AccountFacade

class DefaultAccountController(
     application: Application
) : AbstractAccountController() {

    override val di: DI by di {
        application
    }

    private val facade: AccountFacade by instance("accountFacade")

    override suspend fun create(info: AccountInfo): Account.DTO {
        val accountEntity = facade.create(info.phoneNumber, info.isVerified, info.parentId)
        return accountEntity.toAccount()
    }

    private fun AccountEntity.toAccount() = Account.DTO(
        id, phoneNumber,
        isVerified,
        parentId
    )
}