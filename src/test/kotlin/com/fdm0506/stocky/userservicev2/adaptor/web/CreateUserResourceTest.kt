package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CreateUserResourceTest {

    private val unit: CreateUserResource = CreateUserResource(
            "name",
            "username",
            "password",
            "email"
    )

    @Test
    fun toCommand_returnsCommand_forCreateUserResource() {
        val cmd: CreateUserUseCase.CreateUserCommand = unit.toCommand ()
        assertEquals(CreateUserUseCase.CreateUserCommand::class.java, cmd.javaClass)
    }
}