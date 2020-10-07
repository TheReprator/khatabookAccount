package reprator.khatabookAccount.accountService.controller

import org.kodein.di.ktor.controller.DIController
import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.service.JWTAuthenticatedUser

/**
 * Represents a controller to interface with the balances.
 */
interface AccountController : DIController {
    suspend fun create(info: AccountInfo): Account

    suspend fun refreshToken(
        authenticatedUser: JWTAuthenticatedUser,
        info: AccessTokenInfo
    ): AccessToken

    suspend fun logout(
        authenticatedUser: JWTAuthenticatedUser,
        accessToken: ModelsAccessToken,
        isLogout: Boolean = true
    )
}