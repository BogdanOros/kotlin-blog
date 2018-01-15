package com.boros.configuration.security.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TrustedClientConfiguration {

    companion object {
        const val MOBILE = "security.clients.mobile"
        const val WEB = "security.clients.web"
        const val ADMIN = "security.clients.admin"
    }

    @Bean(MOBILE)
    @ConfigurationProperties(MOBILE)
    fun createMobileClient(): ClientDetail = ClientDetail()

    @Bean(WEB)
    @ConfigurationProperties(WEB)
    fun createWebClient(): ClientDetail = ClientDetail()

    @Bean(ADMIN)
    @ConfigurationProperties(ADMIN)
    fun createAdminClient(): ClientDetail = ClientDetail()

}