package reprator.khatabookAccount.accountService.domain.token

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

interface JWTTokenVerifyingService {
    fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier
}
