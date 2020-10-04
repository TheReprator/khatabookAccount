package reprator.khatabookAccount.service

import io.ktor.auth.*

data class JWTAuthenticatedUser(val userId: Int, val phoneNumber: String,
                                val organizationId: String) : Principal