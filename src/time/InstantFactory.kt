package reprator.khatabookAccount.time

import java.time.Instant

interface InstantFactory {
    fun now(): Instant
}