package reprator.khatabookAccount.auth.token

import reprator.khatabookAccount.auth.JWTUser

interface TokenService {

    fun generateToken(jWTUser: JWTUser): String
}
