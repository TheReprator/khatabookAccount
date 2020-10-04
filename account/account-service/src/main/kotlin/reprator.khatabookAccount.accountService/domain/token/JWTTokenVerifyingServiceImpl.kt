package reprator.khatabookAccount.accountService.domain.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import reprator.khatabookAccount.accountService.domain.token.JwtTokenService.Companion.algorithm
import reprator.khatabookAccount.accountService.domain.token.JwtTokenService.Companion.issuer

open class JWTTokenVerifyingServiceImpl : JWTTokenVerifyingService {

    override fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()

    companion object {
         fun buildJWTVerifier(jwtTokenVerifierService: JWTTokenVerifyingService = JWTTokenVerifyingServiceImpl()): JWTVerifier {
             return jwtTokenVerifierService.buildVerifier(algorithm, issuer)
         }
    }
}
