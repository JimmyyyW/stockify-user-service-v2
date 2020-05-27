package com.fdm0506.stocky.userservicev2.domain.model

import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    fun itConstructsValidUserObject() {
        //val roles: Set<Role> = setOf(Role(ObjectId(), "MEMBER"))
        val actual = User(ObjectId().toHexString(),
                "test",
                "surname",
                "usertest",
                "password",
                "email",
                Decimal128.parse("500.0"),
                false)

        assertTrue(actual::class == User::class)
        assertEquals(actual.name, "test")
        assertEquals(actual.username, "usertest")
    }

}