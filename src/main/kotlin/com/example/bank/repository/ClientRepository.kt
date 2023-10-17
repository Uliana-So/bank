package com.example.bank.repository

import com.example.bank.entity.Client
import org.springframework.data.repository.CrudRepository

interface ClientRepository : CrudRepository<Client, Long> {}