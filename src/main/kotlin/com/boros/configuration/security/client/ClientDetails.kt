package com.boros.configuration.security.client

interface ClientDetails {
    val id: String
    val secret: String
    val role: String
}