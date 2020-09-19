package reprator.khatabookAccount.accountService

import reprator.khatabookAccount.error.StatusCodeException

/**
 * Thrown when an account already exist in the system., while creating a new account
 */
class AccountExistException(message: String? = null, cause: Throwable? = null) : StatusCodeException.BadRequest(message, cause)
class AccountNotExistException(message: String? = null, cause: Throwable? = null) : StatusCodeException.BadRequest(message, cause)

/**
 * Invalid Data or parameter with the request
 */
class AccountInvalidData(message: String? = null, cause: Throwable? = null) : StatusCodeException.BadRequest(message, cause)
