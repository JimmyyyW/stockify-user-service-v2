package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.adaptor.web.ActivateUserRequestResource
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.ActivateUserPort
import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*
import reactor.core.publisher.Mono
import java.time.LocalDateTime

internal class CreateUserServiceTest {

    private var savePort: SaveUserPort = mock(SaveUserPort::class.java)
    private var findPort: FindUserPort = mock(FindUserPort::class.java)
    private var activatePort: ActivateUserPort = mock(ActivateUserPort::class.java)
    private var unit = CreateUserService(savePort, findPort, activatePort)
    private val user: User = User(
            "5e35b230bbf34d4de013f9da",
            "name",
            "surname",
            "username",
            "password",
            "email@email.com",
            Decimal128.parse("500.00"),
            true,
            LocalDateTime.now())

    //given_when_then
    @Test
    fun validCreateUserCommand_portCalledByService_ReturnsSuccess() {
        val command = CreateUserUseCase.CreateUserCommand(Mono.just(user))
        `when`(savePort.saveNewUser(command.user)).thenAnswer { user }

        val actual: Mono<CreateUserResponse> = unit.createUser(command)
        verify(savePort, times(1)).saveNewUser(command.user)

        assertEquals("success", actual.block()?.outcome)
        assertEquals(user, actual.block()?.user)
    }

}