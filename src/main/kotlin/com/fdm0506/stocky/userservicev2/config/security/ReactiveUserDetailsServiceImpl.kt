package com.fdm0506.stocky.userservicev2.config.security

import com.fdm0506.stocky.userservicev2.adaptor.persistence.RxUserRepository
import mu.KotlinLogging
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.lang.Exception

private val logger = KotlinLogging.logger {  }

@Service
class ReactiveUserDetailsServiceImpl(val rxUserRepository: RxUserRepository): ReactiveUserDetailsService {


    override fun findByUsername(p0: String?): Mono<UserDetails> {
        return rxUserRepository.findByUsername(p0!!)
                .map {
                    User.withUsername(p0).password(it.password)
                            .roles("MEMBER")
                            .build()
                }
                .switchIfEmpty(
                     Mono.defer{
                         logger.warn { "Invalid login attempt" }
                         error(UsernameNotFoundException("Username not in database"))
                     })
    }

}