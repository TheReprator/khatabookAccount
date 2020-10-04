package reprator.khatabookAccount.accountService

import reprator.khatabookAccount.error.StatusCodeException

/**
 * Invalid Data or parameter with the request
 */
class AccountInvalidData(message: String? = null, cause: Throwable? = null) : StatusCodeException.BadRequest(message, cause)
class AccountInvalidAuthorization(message: String? = null, cause: Throwable? = null) : StatusCodeException.UnAuthorizedUser(message, cause)
