package com.boros.configuration.security.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface ClientService {
    fun loadClientById(id: String): ClientDetails
}

class ClientNotFoundException(override val message: String) : RuntimeException()

@Service
class TrustedClientService @Autowired
constructor(private val clients: List<ClientDetails>) : ClientService {

    override fun loadClientById(id: String): ClientDetails {
        return clients.find { it.id == id } ?: throw ClientNotFoundException("Client with $id not found")
    }
}