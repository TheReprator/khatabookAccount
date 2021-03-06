package reprator.khatabookAccount

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import org.kodein.di.ktor.di
import org.slf4j.event.Level
import reprator.khatabookAccount.accountService.accountModule
import reprator.khatabookAccount.accountService.authenticationJWT
import reprator.khatabookAccount.accountService.jwtModule
import reprator.khatabookAccount.accountService.moduleAccount
import reprator.khatabookAccount.db.DatabaseConnection
import reprator.khatabookAccount.error.ErrorFeature

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module() {

    install(ErrorFeature)

    install(CallId) {
        generate(10)
    }

    install(CallLogging) {
        level = Level.INFO
        callIdMdc("X-Request-ID")
    }

    install(Authentication){
        authenticationJWT()
    }

    DatabaseConnection.connect()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON

            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            setSerializationInclusion(JsonInclude.Include.NON_NULL)

            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            findAndRegisterModules()
        }
    }

    install(DefaultHeaders) {
        header(HttpHeaders.Accept, ContentType.Application.Json.toString())
    }


    di {
        import(accountModule)
        import(jwtModule)
    }

    moduleAccount()
}
/*

private suspend fun Application.validateCredential(jwtCredential: JWTCredential) =
    if (accountRepository.isUserExist(
            jwtCredential.payload.getClaim("").asString(),
            jwtCredential.payload.getClaim("").asString().toInt()
        )
    ) {
        JWTUser(
            jwtCredential.payload.getClaim("").asString(),
            jwtCredential.payload.getClaim("").asString()
        )
    } else {
        null
    }*/
