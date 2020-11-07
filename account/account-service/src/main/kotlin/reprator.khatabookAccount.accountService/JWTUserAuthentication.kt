package reprator.khatabookAccount.accountService

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.auth.*
import reprator.khatabookAccount.accountService.data.AccountAccessTokenRepository
import reprator.khatabookAccount.accountService.data.db.DbAccountAccessTokenRepository
import reprator.khatabookAccount.accountService.domain.token.JWTTokenVerifyingServiceImpl
import reprator.khatabookAccount.accountService.domain.token.JwtTokenService
import reprator.khatabookAccount.error.StatusCodeException
import reprator.khatabookAccount.service.AUTH_SCHEME
import reprator.khatabookAccount.service.JWTAuthenticatedUser

//val accountTokenRepository = DI(false) {}.direct.instance<AccountAccessTokenRepository>("accountAccessTokenRepository")
/*
val accountTokenRepositsdory = di().di.instance<AccountAccessTokenRepository>("accountAccessTokenRepository")
val accountTokenRepositoryx: AccountAccessTokenRepository = DI().container.instance<AccountAccessTokenRepository>("accountAccessTokenRepository")
*/

fun Authentication.Configuration.authenticationJWT() {

    jwt {

        realm = "reprator.khatabookAccount"

        verifier(JWTTokenVerifyingServiceImpl.buildJWTVerifier())
        authSchemes(AUTH_SCHEME)

        validate {
            val userId = it.payload.getClaim(JwtTokenService.CLAIM_USER_ID).asInt()
            val phoneNumber = it.payload.getClaim(JwtTokenService.CLAIM_USER_PHONE).asString()
            val organizationId = it.payload.getClaim(JwtTokenService.CLAIM_USER_ORGANIZATION_ID).asInt().toString()

            val invalidTokenId: () -> Nothing = {
                throw StatusCodeException.UnAuthorizedUser("Invalid User")
            }

            if (-1 > userId)
                invalidTokenId()

            if (phoneNumber.isNullOrEmpty())
                invalidTokenId()

            val authHeader = request.parseAuthorizationHeader()
            if (!(authHeader?.authScheme == AUTH_SCHEME && authHeader is HttpAuthHeader.Single))
                invalidTokenId()

            val accessToken = authHeader.blob
            if (accessToken.isEmpty())
                invalidTokenId()

            val accountTokenRepository: AccountAccessTokenRepository = DbAccountAccessTokenRepository()
            if (!accountTokenRepository.isTokenValid(userId, accessToken))
                invalidTokenId()

            JWTAuthenticatedUser(userId, phoneNumber, organizationId)
        }
    }
}