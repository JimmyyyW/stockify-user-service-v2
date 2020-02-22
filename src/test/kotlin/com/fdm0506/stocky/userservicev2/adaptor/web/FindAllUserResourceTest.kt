package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.FindAllUserUseCase
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FindAllUserResourceTest {

    private val unit: FindAllUserResource = FindAllUserResource()

    @Test
    fun toCommand_returnsCommandObject_forFindAllUserResource() {
        val cmd: FindAllUserUseCase.FindAllUserCommand = unit.toCommand(null)
        assertEquals(FindAllUserUseCase.FindAllUserCommand::class.java, cmd.javaClass)
    }

    @Test
    fun toCommand_returnsCommandObjectWithSearchProperty_forFindAllUserResource() {
        val cmd: FindAllUserUseCase.FindAllUserCommand = unit.toCommand("regex")
        assertEquals(FindAllUserUseCase.FindAllUserCommand::class.java, cmd.javaClass)
        assertEquals("regex", cmd.search)
    }
}