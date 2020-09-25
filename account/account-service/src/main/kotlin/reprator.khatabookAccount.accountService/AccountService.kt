package reprator.khatabookAccount.accountService

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance
import org.kodein.di.ktor.controller.controller
import reprator.khatabookAccount.accountService.controller.default.DefaultAccountController
import reprator.khatabookAccount.accountService.data.db.TableUser

fun Application.moduleAccount() {

    routing {
        controller { DefaultAccountController(instance()) }
    }

    /*   di{
           import(account)
       }
   */
    transaction {
        SchemaUtils.create(TableUser)
    }

}
