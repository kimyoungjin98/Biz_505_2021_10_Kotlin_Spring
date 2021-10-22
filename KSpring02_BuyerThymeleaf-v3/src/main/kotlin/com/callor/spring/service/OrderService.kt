package com.callor.spring.service

import com.callor.spring.model.Sales

interface OrderService {

    fun selectAll():Array<Sales>
    fun findById(seq : Long):Sales

    // 복수데이터가 될 수 있으므로 Array로 return
    fun findByUserId(userid:String):Array<Sales>
    fun findByPName(pname:String):Array<Sales>

    // 날짜범위를 지정하여 검색하기
    fun findByDateDestance(sDate:String, eDate:String) : Array<Sales>

    fun insert(sales: Sales):Sales
    fun delete(seq: Long)
    fun update(sales: Sales):Sales
}