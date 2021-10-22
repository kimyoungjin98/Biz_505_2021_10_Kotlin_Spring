package com.callor.spring.service

import com.callor.spring.model.Buyer

interface BuyerService {

    fun selectAll(): Array<Buyer>
    fun findById(userid: String): Buyer
    fun findByName(name: String): Array<Buyer>
    fun findByTel(name: String): Array<Buyer>

    fun insert(buyer: Buyer): Buyer
    fun delete(userid: String)
    fun update(buyer: Buyer): Buyer
//    fun insert(buyer: Buyer): Int
//    fun delete(buyer: Buyer): Int
//    fun update(buyer: Buyer): Int


}