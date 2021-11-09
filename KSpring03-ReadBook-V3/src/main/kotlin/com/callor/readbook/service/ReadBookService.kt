package com.callor.readbook.service

import com.callor.readbook.model.ReadBookDTO

interface ReadBookService {

    fun readBookInsert(readBook : ReadBookDTO);

}