package reprator.khatabookAccount.accountService.domain.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import java.util.*

inline class Milliseconds(val expirationPeriod: Long)

class JwtTokenService(
    private val algorithm: Algorithm,
    private val issuer: String
) : JWTTokenVerifyingServiceImpl(), TokenService {

    companion object {
        const val CLAIM_USER_PHONE = "userPhoneNumber"
        const val CLAIM_USER_ORGANIZATION_ID = "userOrganizationId"
        const val CLAIM_USER_ID = "userId"

        private const val secret = "Reprator_Khatabook.Learning"    // use your own secret
        const val issuer = "reprator.khatabookAccount"      // use your own issuer

        val algorithm = Algorithm.HMAC512(secret)

        fun getTokenService(): TokenService {
            return JwtTokenService(algorithm, issuer)
        }
    }

    private fun generateToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        orgnaizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withAudience()
        .withClaim(CLAIM_USER_ID, userId)
        .withClaim(CLAIM_USER_PHONE, phoneNumber)
        .withClaim(CLAIM_USER_ORGANIZATION_ID, orgnaizationId)
        .withExpiresAt(obtainExpirationDate(expirationPeriod))
        .sign(algorithm)


    private fun obtainExpirationDate(expirationPeriod: Milliseconds) = Date(
        System.currentTimeMillis() +
                expirationPeriod.expirationPeriod
    )

    override fun generateAccessToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        orgnaizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ) = generateToken(userId, phoneNumber, orgnaizationId, expirationPeriod)

    override fun generateRefreshToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        orgnaizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ) = generateToken(userId, phoneNumber, orgnaizationId, expirationPeriod)

}
