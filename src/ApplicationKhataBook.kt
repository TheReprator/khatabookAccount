package reprator.khatabookAccount

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.transactions.transaction
import reprator.khatabookAccount.db.tables.EntityUserDao
import reprator.khatabookAccount.service.setup
import reprator.khatabookAccount.socialAuth.firebaseAuthModule

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    setup(testing) {
        import(firebaseAuthModule)
    }
    routing {
        get("/") {
            call.respond("Hello Vikram")
        }
    }


    transaction {
            EntityUserDao.new {
                mobile = "9041866058"
                isVerified = true
        }
    }
}