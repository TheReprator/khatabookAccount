package reprator.khatabookAccount.accountService.data.db

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import reprator.khatabookAccount.accountApi.*
import reprator.khatabookAccount.accountService.AccountNotExistException
import reprator.khatabookAccount.accountService.data.AccountRepository

class DbAccountRepository : AccountRepository {

    override suspend fun save(
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

    override suspend fun select(argsPhoneNumber: PhoneNumber, organizationId: ParentOrganization): Account {
        val result = transaction {
             EntityUserDao.find {
                TableUser.mobile eq argsPhoneNumber and (TableUser.parentId eq organizationId)
            }.limit(1).singleOrNull()
        }
        if (null == result)
            throw AccountNotExistException("User doesn't exist")
        else
            return Account.DTO(result.id.value, result.mobile, result.isVerified, result.parentId)
    }
}