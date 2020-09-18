package reprator.khatabookAccount.accountService.controller.default

import io.ktor.application.*
import reprator.khatabookAccount.accountApi.Account
import reprator.khatabookAccount.accountApi.AccountInfo
import reprator.khatabookAccount.accountService.controller.AbstractAccountController
import reprator.khatabookAccount.accountService.domain.AccountEntity
import reprator.khatabookAccount.accountService.domain.AccountFacade

class DefaultAccountController(
    application: Application
) : AbstractAccountController() {

    override val di by di { application }

    private val facade by di().instance<AccountFacade>()

    override suspend fun create(info: AccountInfo): Account.DTO {
        val accountEntity = facade.create(info.phoneNumber, info.isVerified, info.parentId)
        return accountEntity.toAccount()
    }

    private fun AccountEntity.toAccount()
            = Account.DTO(id, phoneNumber,
        isVerified,
        parentId)
}