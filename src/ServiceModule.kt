package reprator.khatabookAccount

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import reprator.khatabookAccount.auth.token.JWTTokenVerifyingService
import reprator.khatabookAccount.auth.token.JWTTokenVerifyingServiceImpl
import reprator.khatabookAccount.auth.token.JwtTokenService.Companion.getTokenService
import reprator.khatabookAccount.auth.token.TokenService

val jwtModule
    get() = DI.Module("kodeinJWT") {

        bind<TokenService>() with singleton {
            getTokenService()
        }

        bind<JWTTokenVerifyingService>() with singleton {
            JWTTokenVerifyingServiceImpl()
        }
    }
