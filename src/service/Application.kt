package reprator.khatabookAccount.service

import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.Accept
import io.ktor.jackson.jackson
import io.ktor.websocket.WebSockets
import org.kodein.di.Kodein
import org.kodein.di.ktor.controller.KodeinControllerFeature
import org.kodein.di.ktor.kodein
import org.slf4j.event.Level
import reprator.khatabookAccount.db.DatabaseConnection
import reprator.khatabookAccount.error.ErrorFeature

fun Application.setup(testing: Boolean, configuration: Kodein.MainBuilder.() -> Unit) {

    install(ErrorFeature)

    install(CallId) {
        generate(10)
    }

    install(CallLogging) {
        level = Level.INFO
        callIdMdc("X-Request-ID")
    }

    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            findAndRegisterModules()
        }
    }

    install(Authentication) {
    }

    install(DefaultHeaders) {
        header(Accept, Json.toString())
    }

    kodein {
        import(service(testing))
        configuration()
    }

    install(KodeinControllerFeature)

    // WebSockets
    install(WebSockets) {
        timeoutMillis = 30_000
        pingPeriodMillis = 15_000
    }

    // Initialize database
    DatabaseConnection.connect()
}

