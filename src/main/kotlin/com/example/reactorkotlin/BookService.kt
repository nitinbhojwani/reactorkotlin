package com.example.reactorkotlin

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Instant
import java.util.stream.Stream

@Service
class BookService {
    fun produceBooksEverySec() = Flux
            .fromStream(Stream.generate {
                val instant = Instant.now()
                Book(name = "Book $instant", authorName = "Author $instant")
            })
            .delayElements(Duration.ofSeconds(1))
            // .log() logs the stream and we can clearly see this is a cold publisher
            // and it publishes till the client consumes
            .log()
}
