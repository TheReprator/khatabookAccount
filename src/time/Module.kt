package reprator.khatabookAccount.time

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import reprator.khatabookAccount.time.default.DefaultInstantFactory

val time
    get() = Kodein.Module("time") {
        bind<InstantFactory>() with singleton { DefaultInstantFactory() }
    }