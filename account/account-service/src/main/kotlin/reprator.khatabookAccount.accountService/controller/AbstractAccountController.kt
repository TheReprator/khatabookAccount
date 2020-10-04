package reprator.khatabookAccount.accountService.controller

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import reprator.khatabookAccount.accountApi.ACCOUNTS_ENDPOINT
import reprator.khatabookAccount.accountApi.ACCOUNTS_REFRESHTOKEN_ENDPOINT
import reprator.khatabookAccount.accountApi.AccessTokenInfo
import reprator.khatabookAccount.accountApi.AccountInfo
import reprator.khatabookAccount.service.JWTAuthenticatedUser

abstract class AbstractAccountController : AccountController {

    override fun Route.getRoutes() {
        post(ACCOUNTS_ENDPOINT) {
            call.respond(Created, create(call.receive<AccountInfo.DTO>()))
        }

        authenticate {
            post(ACCOUNTS_REFRESHTOKEN_ENDPOINT) {
                val authUser = call.authentication.principal<JWTAuthenticatedUser>()!!
                call.respond(Created, refreshToken(authUser, call.receive<AccessTokenInfo.DTO>()))
            }
        }
    }
}