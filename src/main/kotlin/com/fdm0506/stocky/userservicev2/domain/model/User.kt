package com.fdm0506.stocky.userservicev2.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import lombok.NoArgsConstructor
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Document(collection = "users-v2")
@NoArgsConstructor
data class User(@Id val _id: ObjectId,
                val name: String,
                val username: String,
                val password: String,
                val email: String,
                var balance: Decimal128? = Decimal128.parse("500.00"),
                var enabled: Boolean? = false,
                val timeStamp: LocalDateTime? = LocalDateTime.now(),
                val roles: Set<Role>? = setOf(Role(ObjectId("5dd937401c9d440000fce615"), "MEMBER"))
)

//@Document
//class User private constructor(builder: UserBuilder) {
//    @Id
//    var _id: ObjectId = ObjectId()
//    val name: String?
//    val username: String?
//    val password: String?
//    val email: String?
//    val balance: Decimal128?
//    val enabled: Boolean?
//    val roles: Set<Role>?
//
//    init {
//        this.name = builder.name
//        this.username = builder.username
//        this.password = builder.password
//        this.email = builder.email
//        this.balance = builder.balance
//        this.enabled = builder.enabled
//        this.roles = builder.roles
//    }
//        data class UserBuilder(
//                internal var name: String? = null,
//                internal var username: String? = null,
//                internal var password: String? = null,
//                internal var email: String? = null,
//                internal var balance: Decimal128? = null,
//                internal var enabled: Boolean? = null,
//                internal var roles: Set<Role>? = null) {
//            fun name(name: String): UserBuilder {
//                this.name = name
//                return this
//            }
//            fun username(username: String): UserBuilder {
//                this.username = username
//                return this
//            }
//            fun password(password: String): UserBuilder {
//                this.password = password
//                return this
//            }
//            fun email(email: String): UserBuilder {
//                this.email = email
//                return this
//            }
//            fun balance(balance: Decimal128): UserBuilder {
//                this.balance = balance
//                return this
//            }
//            fun enabled(enabled: Boolean): UserBuilder {
//                this.enabled = enabled
//                return this
//            }
//            fun roles(roles: Set<Role>): UserBuilder {
//                this.roles = roles
//                return this
//            }
//            fun build(): User {
//                return User(this)
//        }
//    }
//}