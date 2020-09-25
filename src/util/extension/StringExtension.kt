package reprator.khatabookAccount.util.extension

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
inline fun requireNotNullAndNotBlank(value: String?, lazyMessage: () -> String) {
    contract {
        returns() implies (value != null)
    }

    require(value.isNullOrBlank().not(), lazyMessage)
    return
}
