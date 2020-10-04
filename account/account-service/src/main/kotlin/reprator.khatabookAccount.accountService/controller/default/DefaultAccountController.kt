package reprator.khatabookAccount.accountService.controller.default

import io.ktor.application.*
import org.kodein.di.DI
import org.kodein.di.instance
import org.kodein.di.ktor.di
import reprator.khatabookAccount.accountApi.AccessToken
import reprator.khatabookAccount.accountApi.AccessTokenInfo
import reprator.khatabookAccount.accountApi.Account
import reprator.khatabookAccount.accountApi.AccountInfo
import reprator.khatabookAccount.accountService.controller.AbstractAccountController
import reprator.khatabookAccount.accountService.domain.AccountEntity
import reprator.khatabookAccount.accountService.domain.AccountFacade
import reprator.khatabookAccount.service.JWTAuthenticatedUser

class DefaultAccountController(
    application: Application
) : AbstractAccountController() {

    override val di: DI by di {
        application
    }

    private val facade: AccountFacade by instance("accountFacade")

    override suspend fun create(info: AccountInfo): Account.DTO {
        val accountEntity = facade.create(info.phoneNumber, info.isVerified, info.parentId)
        return accountEntity.toAccount()
    }

    override suspend fun refreshToken(authenticatedUser: JWTAuthenticatedUser, info: AccessTokenInfo): AccessToken {
        val accountEntity = facade.refreshToken(info.accessToken, info.refreshToken, authenticatedUser)
        return AccessToken.DTO(accountEntity.accessToken, accountEntity.refreshToken)
    }

    private fun AccountEntity.toAccount() = Account.DTO(
        id, phoneNumber,
        isVerified,
        parentId,
        accessToken,
        refreshToken
    )
}