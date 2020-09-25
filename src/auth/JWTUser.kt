package reprator.khatabookAccount.auth

import io.ktor.auth.*

data class JWTUser(val phoneNumber: String, val organizationId: String): Principal