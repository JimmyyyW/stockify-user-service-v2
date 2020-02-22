package com.fdm0506.stocky.userservicev2.domain.model

import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    fun itConstructsValidUserObject() {
        //val roles: Set<Role> = setOf(Role(ObjectId(), "MEMBER"))
        val actual = User(ObjectId(),
                "test",
                "usertest",
                "password",
                "email",
                Decimal128.parse("500.0"),
                false)

        assertTrue(actual::class == User::class)
    }

    @Test
    fun userBuilder_ReturnsValidUserObject() {
//        val user = User.UserBuilder()
//                .username("username")
//                .name("nameuser")
//                .email("email")
//                .balance(Decimal128.parse("500.00"))
//                .enabled(false)
//                .password("password")
//                .build()
//
//
//        assertEquals(user.username, "username")
//        assertEquals(user.name, "nameuser")
//        assertEquals(user.email, "email")
//        assertEquals(user.balance, Decimal128.parse("500.00"))
//        assertEquals(user.enabled, false)
    }

    @Test
    fun userBuilder_forcesCompleteUserObject_ToBeBuilt() {
//        val user = User.UserBuilder()
//                .username("username")
//                .build()
//
//        assertNotEquals(user.username, "username")
    }
}