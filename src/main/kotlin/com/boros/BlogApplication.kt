package com.boros

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * @author: orossbogdan@gmail.com (Bogdan Oros)
 * @date: 14.01.18
 */

@SpringBootApplication
class BlogApplication

@RestController
class PingEndpoint {

    private val logger: Logger = LoggerFactory.getLogger(PingEndpoint::class.java)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/api/ping")
    fun ping() {
        logger.info("Application pinged")
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(BlogApplication::class.java, *args)
}