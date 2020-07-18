package com.example.reactorkotlin

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Component
class SampleBooksInitializer(private val bookRepository: BookRepository) {
    @EventListener(ApplicationReadyEvent::class)
    public fun initialize() {
        val names = Flux.just("Book1", "Book2", "Book3", "Book4", "Book5", "Book6", "Book7")
        val authorNames = Flux.just("Author1", "Author2", "Author3", "Author4", "Author5", "Author6", "Author7")

        val saved : Flux<Book> = Flux.zip(names, authorNames)
                .map {
                    Book(name=it.t1, authorName = it.t2)
                }
                .flatMap{bookRepository.save(it)}

        bookRepository.deleteAll()
                .thenMany(saved)
                .thenMany(bookRepository.findAll())
                .subscribe(::println)
    }
}
