package reprator.khatabookAccount.accountService.data.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object TableUser : IntIdTable(name = "Users") {
    val mobile = text("mobileNumber")
    val verificationStatus = bool("isVerified").default(false)
    val parentId = integer("parentId").default(-1)
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
}

class EntityUserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EntityUserDao>(TableUser)

    var mobile by TableUser.mobile
    var isVerified by TableUser.verificationStatus
    var parentId by TableUser.parentId
    var createdAt by TableUser.createdAt
}

object TableUserAccessToken : Table(name = "AccessToken") {
    val createdAt = datetime("created_at").clientDefault { DateTime.now() }
    val updatedAt = datetime("updated_at").nullable()
    val accessToken = text("accessToken")
    val refreshToken = text("refreshToken")
    val isActive = bool("isActive").clientDefault { true }
    val userId = integer("userId").references(TableUser.id, onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE)
}