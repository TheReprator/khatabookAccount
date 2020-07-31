package reprator.khatabookAccount.time.default

import reprator.khatabookAccount.time.InstantFactory
import java.time.Instant

class DefaultInstantFactory : InstantFactory {
    override fun now(): Instant = Instant.now()
}