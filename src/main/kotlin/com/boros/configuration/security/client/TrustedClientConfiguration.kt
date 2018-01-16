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
    fun createMobileClient(): ClientDetails = BaseClientDetails()

    @Bean(WEB)
    @ConfigurationProperties(WEB)
    fun createWebClient(): ClientDetails = BaseClientDetails()

    @Bean(ADMIN)
    @ConfigurationProperties(ADMIN)
    fun createAdminClient(): ClientDetails = BaseClientDetails()

    private class BaseClientDetails : ClientDetails {
        override lateinit var id: String
        override lateinit var secret: String
        override lateinit var role: String
    }

}