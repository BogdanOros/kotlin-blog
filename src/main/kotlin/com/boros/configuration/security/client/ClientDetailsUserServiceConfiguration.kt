package com.boros.configuration.security.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class ClientDetailsUserServiceConfiguration @Autowired
constructor(private val clients: MutableMap<String, ClientDetail>) {

    @Bean
    fun detailsService(): UserDetailsService {
        return InMemoryUserDetailsManager().apply {
            clients.values.forEach { detail -> createUser(buildUserFromClientDetails(detail)) }
        }
    }

    fun buildUserFromClientDetails(detail: ClientDetail): UserDetails {
        return User.withUsername(detail.id)
                .password(detail.secret)
                .roles(detail.role)
                .build()
    }

}