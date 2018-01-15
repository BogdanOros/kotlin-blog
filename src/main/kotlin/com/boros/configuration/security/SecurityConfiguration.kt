package com.boros.configuration.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration {

    @Configuration
    @Order(2)
    class BasicSecurityConfiguration : WebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity?) {
            http!!.csrf().and().cors().disable()

            http.authorizeRequests()
                    .antMatchers("/api/**")
                    .authenticated()
                    .and()
                    .httpBasic()
        }
    }

}
