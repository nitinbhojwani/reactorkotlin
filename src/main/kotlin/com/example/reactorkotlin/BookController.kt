package com.example.reactorkotlin

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val booksRepository: BookRepository,
                     private val bookService: BookService) {
    @GetMapping("/books")
    fun booksPublisher() = booksRepository.findAll()

    @GetMapping("/sse/books", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stringPublisher() = bookService.produceBooksEverySec()
}