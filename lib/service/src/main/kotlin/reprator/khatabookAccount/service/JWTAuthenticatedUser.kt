package reprator.khatabookAccount.service

import io.ktor.auth.*

const val AUTH_SCHEME = "Bearer"
data class JWTAuthenticatedUser(val userId: Int, val phoneNumber: String,
                                val organizationId: String) : Principal