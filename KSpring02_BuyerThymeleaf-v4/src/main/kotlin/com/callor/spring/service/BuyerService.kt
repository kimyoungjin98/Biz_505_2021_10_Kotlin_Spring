package com.callor.spring.service

import com.callor.spring.model.Buyer

interface BuyerService {

    fun selectAll(): Array<Buyer>
    fun findById(userid: String): Buyer
    fun findByName(name: String): Array<Buyer>
    fun findByTel(tel: String): Array<Buyer>

    fun insert(buyer: Buyer): Buyer
    fun delete(userid: String)
    fun update(buyer: Buyer): Buyer
    fun selectWithPageable(intPage: Int): Array<Buyer>
}