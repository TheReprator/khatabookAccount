package reprator.khatabookAccount.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import reprator.khatabookAccount.auth.token.JwtTokenService.Companion.algorithm
import reprator.khatabookAccount.auth.token.JwtTokenService.Companion.issuer

open class JWTTokenVerifyingServiceImpl : JWTTokenVerifyingService {

    override fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()

    companion object {

        fun buildJWTVerifier(jwtTokenVerifierService: JWTTokenVerifyingService): JWTVerifier {
            return jwtTokenVerifierService.buildVerifier(algorithm, issuer)
        }
    }
}
