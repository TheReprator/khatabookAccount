package reprator.khatabookAccount.service

import io.ktor.application.Application
import org.kodein.di.Kodein
import reprator.khatabookAccount.identifier.identifier
import reprator.khatabookAccount.time.time

fun Application.service(test: Boolean) = Kodein.Module("service") {
    import(identifier)
    import(time)
}