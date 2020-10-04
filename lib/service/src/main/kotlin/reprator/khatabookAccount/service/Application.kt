package reprator.khatabookAccount.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.Accept
import io.ktor.jackson.*
import org.kodein.di.DI
import org.kodein.di.ktor.di
import org.slf4j.event.Level
import reprator.khatabookAccount.error.ErrorFeature

/*
fun Application.setup(configuration: DI.MainBuilder.() -> Unit) {

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
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON

            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            findAndRegisterModules()
        }
    }

    install(DefaultHeaders) {
        header(Accept, Json.toString())
    }

    di {
        import(service())
        configuration()
    }
}
*/

