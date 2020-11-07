package reprator.khatabookAccount.accountService.domain.token

import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ModelsAccessToken
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber

interface TokenService {

    companion object {
        private val tokenExpiration: Milliseconds = Milliseconds((60 * 60 * 1000) * 24)   // 1 day
        private val refreshTokenExpiration: Milliseconds = Milliseconds(((60 * 60 * 1000) * 24) * 10)   // 10 day
    }

    fun generateAccessToken(
        userId: AccountId, phoneNumber: PhoneNumber,
        organizationId: ParentOrganization, expirationPeriod: Milliseconds = tokenExpiration
    ): ModelsAccessToken

    fun generateRefreshToken(
        userId: AccountId, phoneNumber: PhoneNumber,
        organizationId: ParentOrganization, expirationPeriod: Milliseconds = refreshTokenExpiration
    ): ModelsAccessToken
}
