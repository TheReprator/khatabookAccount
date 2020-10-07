package reprator.khatabookAccount.accountService.data.db

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ModelsAccessToken
import reprator.khatabookAccount.accountService.data.AccountAccessTokenRepository

class DbAccountAccessTokenRepository : AccountAccessTokenRepository {

    override suspend fun saveUserToken(
        argAccountId: AccountId,
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken
    ) {
        transaction {
            TableUserAccessToken.insert {
                it[userId] = argAccountId
                it[accessToken] = argAccessToken
                it[refreshToken] = argRefreshToken
            }
        }
    }

    override suspend fun updateUserToken(
        argAccountId: AccountId,
        argAccessToken: ModelsAccessToken,
        argRefreshToken: ModelsAccessToken,
        argDisableToken: Boolean
    ) {
        transaction {
            TableUserAccessToken.update({
                (TableUserAccessToken.userId eq argAccountId) and
                        (TableUserAccessToken.accessToken eq argAccessToken) and
                        (TableUserAccessToken.refreshToken eq argRefreshToken)
            }) {
                it[updatedAt] = DateTime.now()
                it[isActive] = argDisableToken
            }
        }
    }

    override suspend fun disableToken(argAccountId: AccountId, argAccessToken: ModelsAccessToken) {
        transaction {
            TableUserAccessToken.update({
                (TableUserAccessToken.userId eq argAccountId) and
                        (TableUserAccessToken.accessToken eq argAccessToken)
            }) {
                it[updatedAt] = DateTime.now()
                it[isActive] = false
            }
        }
    }
}
