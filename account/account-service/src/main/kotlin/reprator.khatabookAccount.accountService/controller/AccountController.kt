package reprator.khatabookAccount.accountService.controller

import org.kodein.di.ktor.controller.DIController
import reprator.khatabookAccount.accountApi.AccessToken
import reprator.khatabookAccount.accountApi.AccessTokenInfo
import reprator.khatabookAccount.accountApi.Account
import reprator.khatabookAccount.accountApi.AccountInfo
import reprator.khatabookAccount.service.JWTAuthenticatedUser

/**
 * Represents a controller to interface with the balances.
 */
interface AccountController : DIController {
    suspend fun create(info: AccountInfo): Account

    suspend fun refreshToken(authenticatedUser: JWTAuthenticatedUser,
                             info: AccessTokenInfo): AccessToken
}