package reprator.khatabookAccount.accountService.controller

import org.kodein.di.ktor.controller.DIController
import reprator.khatabookAccount.accountApi.Account
import reprator.khatabookAccount.accountApi.AccountInfo

/**
 * Represents a controller to interface with the balances.
 */
interface AccountController : DIController {
    suspend fun create(info: AccountInfo): Account
}