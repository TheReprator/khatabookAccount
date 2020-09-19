package reprator.khatabookAccount.accountService

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import reprator.khatabookAccount.accountService.data.AccountRepository
import reprator.khatabookAccount.accountService.data.db.DbAccountRepository
import reprator.khatabookAccount.accountService.domain.AccountFacade
import reprator.khatabookAccount.accountService.domain.AccountResourceFactory
import reprator.khatabookAccount.accountService.domain.default.DefaultAccountFacade
import reprator.khatabookAccount.accountService.domain.default.DefaultAccountResourceFactory

/**
 * The container to inject all dependencies from the account module.
 */
val account
    get() = DI.Module("account") {
        bind<AccountRepository>("accountRepository") with singleton { DbAccountRepository() }
        bind<AccountFacade>("accountFacade") with singleton { DefaultAccountFacade(di) }
        bind<AccountResourceFactory>("accountResourceFactory") with singleton { DefaultAccountResourceFactory(di) }
    }