package reprator.khatabookAccount.identifier.uuid

import reprator.khatabookAccount.identifier.IdentifierFactory
import java.util.*

class UUIDIdentifierFactory : IdentifierFactory {
    override fun create() = UUID.randomUUID().toString()
}