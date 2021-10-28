package com.callor.readbook.service

import com.callor.readbook.model.Book

interface BookService {
    fun insert(book: Book):Book
}