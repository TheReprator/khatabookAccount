package reprator.khatabookAccount.accountService

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance
import org.kodein.di.ktor.controller.controller
import org.kodein.di.ktor.di
import reprator.khatabookAccount.accountService.controller.default.DefaultAccountController
import reprator.khatabookAccount.accountService.data.db.TableUser
import reprator.khatabookAccount.accountService.data.db.TableUserAccessToken

fun Application.moduleAccount() {

    routing {
        controller { DefaultAccountController(instance()) }
    }

    transaction {
        SchemaUtils.createMissingTablesAndColumns(TableUser, TableUserAccessToken)
    }

    /*di {
        import(accountModule)
        import(jwtModule)
    }*/
}
