package reprator.khatabookAccount.accountService.data.db

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import reprator.khatabookAccount.accountApi.AccountId
import reprator.khatabookAccount.accountApi.ParentOrganization
import reprator.khatabookAccount.accountApi.PhoneNumber
import reprator.khatabookAccount.accountApi.VerificationStatus
import reprator.khatabookAccount.accountService.data.AccountRepository

class DbAccountRepository : AccountRepository {

    override suspend fun saveUser(
        argPhoneNumber: PhoneNumber,
        argIsVerified: VerificationStatus,
        argParentId: ParentOrganization
    ): AccountId {
        return transaction {
            EntityUserDao.new {
                mobile = argPhoneNumber
                isVerified = argIsVerified
                parentId = argParentId
            }.id.value
        }
    }

    override suspend fun getParentOrganization(organizationId: ParentOrganization): Boolean {
        return true
    }

    override suspend fun isUserExist(argsPhoneNumber: PhoneNumber, organizationId: ParentOrganization): Boolean {
        return transaction {
            EntityUserDao.find {
                TableUser.mobile eq argsPhoneNumber and (TableUser.parentId eq organizationId)
            }.empty().not()
        }
    }
}
