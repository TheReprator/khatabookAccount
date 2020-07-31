package reprator.khatabookAccount.db.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

const val VALIDATION_MOBILE_LENGTH_MIN = 9
const val VALIDATION_MOBILE_LENGTH_MAX = 15

object TableUser : IntIdTable(name = "Users") {
    val mobile = text("mobileNumber")
    val verificationStatus = bool("isVerified").default(false)
    val parentId = integer("parentId").default(-1)
}

class EntityUserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EntityUserDao>(TableUser)

    var mobile by TableUser.mobile
    var isVerified by TableUser.verificationStatus
    var parentId by TableUser.parentId
}
