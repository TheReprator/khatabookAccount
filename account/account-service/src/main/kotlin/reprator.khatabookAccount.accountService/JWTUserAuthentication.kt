package reprator.khatabookAccount.accountService

import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import org.kodein.di.instance
import org.kodein.di.ktor.di
import reprator.khatabookAccount.accountService.domain.token.JWTTokenVerifyingService
import reprator.khatabookAccount.accountService.domain.token.JWTTokenVerifyingServiceImpl
import reprator.khatabookAccount.accountService.domain.token.JwtTokenService
import reprator.khatabookAccount.service.AUTH_SCHEME
import reprator.khatabookAccount.service.JWTAuthenticatedUser


fun Authentication.Configuration.authenticationJWT() {

    jwt {

        realm = "reprator.khatabookAccount"

        verifier(JWTTokenVerifyingServiceImpl.buildJWTVerifier())
        authSchemes(AUTH_SCHEME)

        validate {
            val userId = it.payload.getClaim(JwtTokenService.CLAIM_USER_ID).asInt()
            val phoneNumber = it.payload.getClaim(JwtTokenService.CLAIM_USER_PHONE).asString()
            val organizationId = it.payload.getClaim(JwtTokenService.CLAIM_USER_ORGANIZATION_ID).asInt().toString()

            if (-1 > userId) {
                return@validate null
            }

            if (phoneNumber.isNullOrEmpty()) {
                return@validate null
            }

            JWTAuthenticatedUser(userId, phoneNumber, organizationId)
        }
    }
}