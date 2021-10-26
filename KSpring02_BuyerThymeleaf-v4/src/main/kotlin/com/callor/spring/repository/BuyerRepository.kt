package com.callor.spring.repository

import com.callor.spring.model.Buyer
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BuyerRepository : JpaRepository<Buyer, String> {

    /**
     * JpaRepository를 상속받은 Repository에서
     * 기본 CRUD 외에 다른 칼럼으로 조회하는 method를 추가할 수 있다
     * 단, 조건..! - findBy로 시작해야 함.
     * data(Entity) 클래스에 정의된 멤버 변수 이름만 설정이 가능함
     */
    fun findByName(name: String): Array<Buyer>
    fun findByTel(tel: String): Array<Buyer>

//    fun findByUserName(userName:String): Array<Buyer>

    // DSL (Domain Specific Language)
    @Query("SELECT B FROM Buyer B")
    fun findWithPagination(pageable: Pageable?):List<Buyer>

}
