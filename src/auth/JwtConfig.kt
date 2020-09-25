package reprator.khatabookAccount.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.Principal
import java.util.*

object JwtConfig {
    private const val secret = "Reprator_Khatabook.Learning" // use your own secret
    private const val issuer = "reprator.khatabookAccount"  // use your own issuer
    private const val validityInMs = (60*60*1000) * 24 // 1 day

    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    /**
     * Produce a token for this combination of name and password
     */
    fun generateToken(JWTUser: JWTUser): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withAudience()
        //.withClaim("name", JWTUser.name)
        //.withClaim("password", JWTUser.password)
        .withExpiresAt(getExpiration())  // optional
        .sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}


data class JWTUser(val phoneNumber: String, val id: Int,val organizationId: Int): Principal