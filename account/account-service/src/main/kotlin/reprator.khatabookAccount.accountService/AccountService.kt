package reprator.khatabookAccount.accountService

import io.ktor.application.*
import io.ktor.application.install
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.DI
import org.kodein.di.instance
import org.kodein.di.ktor.controller.controller
import org.kodein.di.ktor.di
import reprator.khatabookAccount.accountService.controller.default.DefaultAccountController
import reprator.khatabookAccount.accountService.data.db.TableUser
import reprator.khatabookAccount.error.ErrorFeature

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.moduleAccount() {

    routing {
        controller { DefaultAccountController(instance()) }
    }

 /*   di{
        import(account)
    }
*/
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(TableUser)
    }

}
