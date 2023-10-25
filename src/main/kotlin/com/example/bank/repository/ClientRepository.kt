package com.example.bank.repository

import com.example.bank.entity.Client
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ClientRepository : CrudRepository<Client, UUID> {}