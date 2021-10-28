package com.callor.readbook.service.impl

import com.callor.readbook.model.ReadBook
import com.callor.readbook.repository.ReadBookRepository
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Service

@Service("readBookServiceV1")
class ReadBookServiceImplV1(val rDao:ReadBookRepository):ReadBookService {

    override fun insert(readBook: ReadBook): ReadBook {
        return rDao.save(readBook)
    }

}