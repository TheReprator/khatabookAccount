package reprator.khatabookAccount.error

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*
import mu.KLogging
import javax.naming.AuthenticationException

/**
 * A feature which allows to automatically send an error response on exceptions.
 */
class ErrorFeature private constructor() {

    companion object Feature : KLogging(), ApplicationFeature<Application, Unit, ErrorFeature> {
        override val key: AttributeKey<ErrorFeature> = AttributeKey("ErrorFeature")

        override fun install(pipeline: Application, configure: Unit.() -> Unit): ErrorFeature {
            Unit.configure()

            pipeline.install(StatusPages) {

                exception<Throwable> {
                    logger.error(it) { "Caught exception: ${it.message}" }
                    val status = if (it is StatusCodeException) it.statusCode else InternalServerError
                    call.respond(status, ErrorResponse(status, it.message, call.request.path()))
                }
            }

            return ErrorFeature()
        }

    }
}