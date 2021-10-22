package com.callor.spring.repository

import com.callor.spring.model.Buyer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// 현재 BuyerRepository interface를 bean으로 등록하라
// @Repository 는 선택적인 option
// 굳이 사용하지 않아도 상관없지만 사용하는 것이 좋다
@Repository
interface BuyerRepository:JpaRepository<Buyer,String> {

    // findBy 뒤에 붙은 것은 칼럼으로 존재해야 하며, 존재하지 않는 이름으로 할 경우 오류가 발생함
    /**
     * JpaRepository 를 상속받은 Repository 에서 기본 CRUD 이외에
     * 다른 칼럼으로 조회하는 method 를 추가할 수 있다
     * 단, 조건이 있는데
     * findBy로 시작해야 하며
     * data(Entity) 클래스에 정읟된 맴버 변수 이름만 설정이 가능하다.
     */

    fun findByName(name:String):Array<Buyer>;
    fun findByTel(tel:String):Array<Buyer>


}