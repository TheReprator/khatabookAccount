package reprator.khatabookAccount.accountService.controller

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.accountService.AccountInvalidData
import reprator.khatabookAccount.service.AUTH_SCHEME
import reprator.khatabookAccount.service.JWTAuthenticatedUser

abstract class AbstractAccountController : AccountController {

    override fun Route.getRoutes() {
        post(ACCOUNTS_ENDPOINT) {
            call.respond(Created, create(call.receive<AccountInfo.DTO>()))
        }

        authenticate {
            post(ACCOUNTS_REFRESHTOKEN_ENDPOINT) {
                val accessToken = call.request.header(Authorization)!!.substringAfter(AUTH_SCHEME, "none")
                val authUser = call.authentication.principal<JWTAuthenticatedUser>()!!
                val refreshToken = call.receiveParameters()[TOKEN_REFRESH] ?: throw AccountInvalidData("Invalid Data")

                val accessTokenInfo = AccessTokenInfo.DTO(accessToken, refreshToken)

                call.respond(Created, refreshToken(authUser, accessTokenInfo))
            }
        }

        authenticate {
            patch(ACCOUNTS_LOGOUT) {
                val accessToken = call.request.header(Authorization)!!.substringAfter(AUTH_SCHEME, "none")
                val authUser = call.authentication.principal<JWTAuthenticatedUser>()!!

                logout(authUser, accessToken)
                call.respond(OK, RESPONSE_LOGOUT)
            }
        }
    }
}

private const val TOKEN_REFRESH = "refreshToken"
private const val RESPONSE_LOGOUT = "logout successful"