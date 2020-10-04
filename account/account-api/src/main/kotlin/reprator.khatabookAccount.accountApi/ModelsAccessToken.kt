package reprator.khatabookAccount.accountApi

typealias ModelsAccessToken = String

/**
 * Represents the information relevant for an account.
 */
interface AccessTokenInfo {
    val accessToken: ModelsAccessToken
    val refreshToken: ModelsAccessToken

    data class DTO(
        override val accessToken: ModelsAccessToken,
        override val refreshToken: ModelsAccessToken

    ) : AccessTokenInfo
}

/**
 * Represents the information for a persisted account.
 */
interface AccessToken : AccessTokenInfo {

    data class DTO(
        override val accessToken: ModelsAccessToken,
        override val refreshToken: ModelsAccessToken
    ) : AccessToken
}