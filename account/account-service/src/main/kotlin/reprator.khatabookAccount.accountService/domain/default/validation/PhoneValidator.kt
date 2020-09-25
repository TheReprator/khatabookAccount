package reprator.khatabookAccount.accountService.domain.default.validation

import reprator.khatabookAccount.accountApi.PhoneNumber

interface PhoneValidator {
    fun validatePhoneNumber(phoneNumber: PhoneNumber): Boolean
}

private const val LENGTH_PHONENUMBER = 10

object PhoneValidatorImpl : PhoneValidator {

    override fun validatePhoneNumber(phoneNumber: PhoneNumber): Boolean {

        if (LENGTH_PHONENUMBER > phoneNumber.length)
            return false
        else if (LENGTH_PHONENUMBER < phoneNumber.length)
            return false

        return true
    }
}