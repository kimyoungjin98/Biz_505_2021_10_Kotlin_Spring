package com.callor.spring.repository

import com.callor.spring.model.Sales
import org.springframework.data.jpa.repository.JpaRepository


// seq 값이 long 형이기 때문에 <Sales,Long>로 만들어주기
// Repository 인터페이스 생성
// JpaRepository 를 상속받고 데이터 DTO 와 테이블의 PK 값을
// Generic 으로 설정해 주기
interface SalesRepository:JpaRepository<Sales,Long> {

    fun findByPname(pname:String) : Array<Sales>
    fun findByUserid(userid:String) : Array<Sales>
}