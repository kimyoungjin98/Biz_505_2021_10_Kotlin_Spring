package com.callor.readbook.repository

import com.callor.readbook.model.ReadBookDTO
import org.springframework.data.jpa.repository.JpaRepository

interface ReadBookRepository : JpaRepository<ReadBookDTO, Long>{

}