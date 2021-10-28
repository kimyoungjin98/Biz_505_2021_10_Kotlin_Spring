package com.callor.readbook.repository

import com.callor.readbook.model.Book
import com.callor.readbook.model.ReadBook
import org.springframework.boot.SpringBootConfiguration
import org.springframework.data.jpa.repository.JpaRepository

@SpringBootConfiguration
interface ReadBookRepository:JpaRepository<ReadBook, String> {

}