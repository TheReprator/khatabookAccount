package reprator.khatabookAccount.accountService

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import reprator.khatabookAccount.accountService.data.AccountAccessTokenRepository
import reprator.khatabookAccount.accountService.data.AccountRepository
import reprator.khatabookAccount.accountService.data.db.DbAccountAccessTokenRepository
import reprator.khatabookAccount.accountService.data.db.DbAccountRepository
import reprator.khatabookAccount.accountService.domain.AccountFacade
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory
import reprator.khatabookAccount.accountService.domain.default.DefaultAccountFacade
import reprator.khatabookAccount.accountService.domain.default.DefaultAccountResourceFactory
import reprator.khatabookAccount.accountService.domain.token.JWTTokenVerifyingService
import reprator.khatabookAccount.accountService.domain.token.JWTTokenVerifyingServiceImpl
import reprator.khatabookAccount.accountService.domain.token.JwtTokenService
import reprator.khatabookAccount.accountService.domain.token.TokenService

/**
 * The container to inject all dependencies from the account module.
 */
val accountModule
    get() = DI.Module("account") {
        bind<AccountRepository>("accountRepository") with singleton { DbAccountRepository() }
        bind<AccountAccessTokenRepository>("accountAccessTokenRepository") with singleton { DbAccountAccessTokenRepository() }
        bind<AccountFacade>("accountFacade") with singleton { DefaultAccountFacade(di) }
        bind<AccountResourceFactory>("accountResourceFactory") with singleton { DefaultAccountResourceFactory(di) }
    }

val jwtModule
    get() = DI.Module("jwt") {
        bind<TokenService>("tokenService") with singleton { JwtTokenService.getTokenService() }
        bind<JWTTokenVerifyingService>("jWTTokenVerifyingService") with singleton {
            JWTTokenVerifyingServiceImpl()
        }
    }


/*
val jwtModule
    get() = DI.Module("kodeinJWT") {

        bind<TokenService>() with singleton {
            getTokenService()
        }

        bind<JWTTokenVerifyingService>() with singleton {
            JWTTokenVerifyingServiceImpl()
        }
     }
    */
