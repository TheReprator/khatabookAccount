package reprator.khatabookAccount.accountService.domain

import reprator.khatabookAccount.accountApi.*

interface AccessTokenEntity {
    val accessToken: ModelsAccessToken
    val refreshToken: ModelsAccessToken

    data class DTO(
        override val accessToken: ModelsAccessToken,
        override val refreshToken: ModelsAccessToken
    ) : AccessTokenEntity
}
