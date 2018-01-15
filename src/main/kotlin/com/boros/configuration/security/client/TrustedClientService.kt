package com.boros.configuration.security.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface ClientService {
    fun loadClientById(id: String): ClientDetail
}

class ClientNotFoundException(override val message: String) : RuntimeException()

@Service
class TrustedClientService @Autowired
constructor(private val clients: List<ClientDetail>) : ClientService {

    override fun loadClientById(id: String): ClientDetail {
        return clients.find { it.id == id } ?: throw ClientNotFoundException("Client with $id not found")
    }
}