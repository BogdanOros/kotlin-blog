package com.boros.configuration.security.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TrustedClientConfiguration {

    companion object {
        const val MOBILE = "mobile"
        const val WEB = "web"
        const val ADMIN = "admin"

        private const val CLIENTS = "clients."
    }

    @Bean(MOBILE)
    @ConfigurationProperties(CLIENTS + MOBILE)
    fun createMobileClient(): ClientDetail = ClientDetail()

    @Bean(WEB)
    @ConfigurationProperties(CLIENTS + WEB)
    fun createWebClient(): ClientDetail = ClientDetail()

    @Bean(ADMIN)
    @ConfigurationProperties(CLIENTS + ADMIN)
    fun createAdminClient(): ClientDetail = ClientDetail()

}