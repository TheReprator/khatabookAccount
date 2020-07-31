package reprator.khatabookAccount.identifier

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import reprator.khatabookAccount.identifier.uuid.UUIDIdentifierFactory


val identifier get() = Kodein.Module("identifier") {
    bind<IdentifierFactory>() with singleton { UUIDIdentifierFactory() }
}