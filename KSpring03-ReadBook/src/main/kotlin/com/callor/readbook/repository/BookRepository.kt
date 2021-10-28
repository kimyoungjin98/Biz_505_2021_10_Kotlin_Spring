package com.callor.readbook.repository

import com.callor.readbook.model.Book
import org.springframework.boot.SpringBootConfiguration
import org.springframework.data.jpa.repository.JpaRepository

@SpringBootConfiguration
interface BookRepository:JpaRepository<Book, String> {

}