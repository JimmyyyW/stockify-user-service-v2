package com.fdm0506.stocky.userservicev2.config.security

import com.fdm0506.stocky.userservicev2.bCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono
import java.util.*


@EnableWebFluxSecurity
class SecurityConfig(@Autowired val service: ReactiveUserDetailsService) {

    @Bean
    fun springSecurityFilterChain(serverHttpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
        serverHttpSecurity.authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/login").permitAll()
                .pathMatchers(HttpMethod.POST, "/logout").permitAll()
                .pathMatchers(HttpMethod.POST, "/register").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/v2/user/activate/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .formLogin()
                    .authenticationManager(authenticationManager())
                    .requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers( HttpMethod.POST, "/login" ))
                    .authenticationSuccessHandler(RedirectServerAuthenticationSuccessHandler("/home"))
                    .authenticationFailureHandler(ServerAuthenticationEntryPointFailureHandler(HttpBasicServerAuthenticationEntryPoint()))
                    .authenticationEntryPoint(HttpBasicServerAuthenticationEntryPoint())
                .and()
                .logout()
                    .logoutSuccessHandler { exchange, _ -> Mono.fromRunnable { exchange.exchange.response.statusCode = HttpStatus.OK }}

        return serverHttpSecurity.build()
    }

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        val authManager = UserDetailsRepositoryReactiveAuthenticationManager(service)
        authManager.setPasswordEncoder(bCryptPasswordEncoder())
        return authManager
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("http://localhost:4200")
        config.allowedMethods = listOf("GET", "PUT", "POST", "DELETE")
        config.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}