package com.fdm0506.stocky.userservicev2.domain.model

import lombok.Data
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "roles")
data class Role(val role: RoleType)

@Data
enum class RoleType {
    MEMBER, ADMIN
}
