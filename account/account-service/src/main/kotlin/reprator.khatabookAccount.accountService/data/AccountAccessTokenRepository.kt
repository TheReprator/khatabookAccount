package reprator.khatabookAccount.accountService.data

import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ModelsAccessToken

interface AccountAccessTokenRepository {
    suspend fun saveUserToken(
        argAccountId: AccountId,
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken
    )

    suspend fun updateUserToken(
        argAccountId: AccountId,
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken, argDisableToken: Boolean = false
    )

    suspend fun disableToken(
        argAccountId: AccountId,
        argAccessToken: ModelsAccessToken
    )
}