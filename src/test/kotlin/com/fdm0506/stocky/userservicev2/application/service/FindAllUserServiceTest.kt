package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import reactor.core.publisher.Flux
import java.time.LocalDateTime

internal class FindAllUserServiceTest {

    private var port: FindUserPort = mock(FindUserPort::class.java)
    private var unit = FindUserService(port)
    private val user: User = User(ObjectId("5e35b230bbf34d4de013f9da"),
            "name",
            "surname",
            "username",
            "password",
            "email@email.com",
            Decimal128.parse("500.00"),
            true,
            LocalDateTime.now())

    private val user2: User = User(ObjectId("5e35b230bbf34d4de013f9da"),
            "name2",
            "surname2",
            "username2",
            "password2",
            "email2@email.com",
            Decimal128.parse("500.00"),
            true,
            LocalDateTime.now())

    @BeforeEach
    internal fun setUp() {
        unit = FindUserService(port)
    }

    @AfterEach
    internal fun tearDown() {
    }
    //given_when_then
    @Test
    internal fun validFindAllUserCommand_portCalledByService_ReturnsSuccess() {
        val command = FindUserUseCase.FindAllUserCommand(null)
        Mockito.`when`(port.findAllUsers()).thenReturn(Flux.just(user, user2))

        val actual: Flux<User> = unit.findAllUsers(command)
        val actualUser1: User? = actual.blockFirst()
        val actualUser2: User? = actual.blockLast()

        Mockito.verify(port, Mockito.times(1)).findAllUsers()

        assertEquals("name", actualUser1?.name)
        assertEquals("username", actualUser1?.username)
        assertEquals("password", actualUser1?.password)
        assertEquals("email@email.com", actualUser1?.email)
        assertEquals(Decimal128.parse("500.00"), actualUser1?.balance)

        assertEquals("name2", actualUser2?.name)
        assertEquals("username2", actualUser2?.username)
        assertEquals("password2", actualUser2?.password)
        assertEquals("email2@email.com", actualUser2?.email)
        assertEquals(Decimal128.parse("500.00"), actualUser2?.balance)
    }

    @Test //isolated test to verify search value can be passed
    internal fun validFindAllWithSearchUserCommand_portCalledByService_ReturnsSuccess() {
        val command = FindUserUseCase.FindAllUserCommand("username2")
        //assume mongo repo acts rationally
        Mockito.`when`(port.findUserByNameRegex(command.search)).thenReturn(Flux.just(user2))

        //now the same
        val actual: Flux<User> = unit.findAllUsersBySearch(command)
        val actualUser1: User? = actual.blockFirst()
        val actualUser2: User? = actual.blockLast()

        Mockito.verify(port, Mockito.times(1)).findUserByNameRegex(command.search)

        assertEquals("name2", actualUser1?.name)
        assertEquals("username2", actualUser1?.username)
        assertEquals("password2", actualUser1?.password)
        assertEquals("email2@email.com", actualUser1?.email)
        assertEquals(Decimal128.parse("500.00"), actualUser1?.balance)

        assertEquals("name2", actualUser2?.name)
        assertEquals("username2", actualUser2?.username)
        assertEquals("password2", actualUser2?.password)
        assertEquals("email2@email.com", actualUser2?.email)
        assertEquals(Decimal128.parse("500.00"), actualUser2?.balance)
    }
}