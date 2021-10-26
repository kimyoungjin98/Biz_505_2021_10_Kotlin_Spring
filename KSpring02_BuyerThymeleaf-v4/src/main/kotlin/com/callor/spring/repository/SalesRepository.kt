package com.callor.spring.repository

import com.callor.spring.model.Sales
import org.springframework.data.jpa.repository.JpaRepository

// Repository Interface 생성
// JpaRepository를 상속받고 데이터 DTO와 테이블의 PK 값을 Generic으로 설정해준다
interface SalesRepository : JpaRepository<Sales, Long> {

    fun findByPname(pname: String): Array<Sales>
    fun findByUserid(userid: String): Array<Sales>

}