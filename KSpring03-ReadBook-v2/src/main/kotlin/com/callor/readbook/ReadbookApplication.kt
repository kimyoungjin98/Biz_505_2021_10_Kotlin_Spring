package com.callor.readbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReadbookApplication

fun main(args: Array<String>) {
    runApplication<ReadbookApplication>(*args)
}
