package com.example.reactorkotlin

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BookRepository: ReactiveCrudRepository<Book, String>
