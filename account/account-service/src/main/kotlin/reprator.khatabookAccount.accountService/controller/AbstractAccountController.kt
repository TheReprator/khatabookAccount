package reprator.khatabookAccount.accountService.controller

import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import reprator.khatabookAccount.accountApi.ACCOUNTS_ENDPOINT
import reprator.khatabookAccount.accountApi.AccountInfo

abstract class AbstractAccountController : AccountController {

    override fun Route.getRoutes() {
        post(ACCOUNTS_ENDPOINT) {
            call.respond(Created, create(call.receive<AccountInfo.DTO>()))
        }
    }
}