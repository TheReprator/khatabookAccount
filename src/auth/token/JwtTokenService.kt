package reprator.khatabookAccount.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import reprator.khatabookAccount.auth.JWTUser
import reprator.khatabookAccount.auth.Milliseconds
import java.util.*

class JwtTokenService(
    private val algorithm: Algorithm,
    private val expirationPeriod: Milliseconds,
    private val issuer: String
) : JWTTokenVerifyingServiceImpl(), TokenService {

    companion object {
        const val CLAIM_USER_PHONE = "userPhoneNumber"
        const val CLAIM_USER_ORGANIZATION_ID = "userOrganizationId"

        private const val secret = "Reprator_Khatabook.Learning"    // use your own secret
        private const val validityInMs: Long = (60 * 60 * 1000) * 24    // 1 day

        const val issuer = "reprator.khatabookAccount"      // use your own issuer
        val algorithm = Algorithm.HMAC512(secret)

        fun getTokenService(): TokenService {
            return JwtTokenService(algorithm, Milliseconds(validityInMs), issuer)
        }
    }

    override fun generateToken(jWTUser: JWTUser): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withAudience()
        .withClaim(CLAIM_USER_PHONE, jWTUser.phoneNumber)
        .withClaim(CLAIM_USER_ORGANIZATION_ID, jWTUser.organizationId)
        .withExpiresAt(obtainExpirationDate())
        .sign(algorithm)

    private fun obtainExpirationDate() = Date(System.currentTimeMillis() + expirationPeriod.value)
}
