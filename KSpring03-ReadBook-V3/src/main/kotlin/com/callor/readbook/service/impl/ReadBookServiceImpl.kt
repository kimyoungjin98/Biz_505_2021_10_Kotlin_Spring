package com.callor.readbook.service.impl

import com.callor.readbook.model.ReadBookDTO
import com.callor.readbook.repository.ReadBookRepository
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Service

@Service
class ReadBookServiceImpl(val repo:ReadBookRepository):ReadBookService {

    override fun readBookInsert(readBook: ReadBookDTO) {

        repo.save(readBook);
    }


}