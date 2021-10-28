package com.callor.readbook.service.impl

import com.callor.readbook.model.Book
import com.callor.readbook.model.ReadBook
import com.callor.readbook.repository.BookRepository
import com.callor.readbook.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("bookService")
class BookServiceImplV1(val bDao:BookRepository):BookService {


    override fun insert(book: Book): Book {

        return bDao.save(book);

    }
}