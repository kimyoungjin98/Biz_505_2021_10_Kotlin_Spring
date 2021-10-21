package com.callor.spring.service

import com.callor.spring.model.Product
import org.springframework.stereotype.Service

@Service
interface ProductService {
    fun selectAll(): Array<Product>
    fun findById(userid: String): Product
    fun findByName(name: String): Array<Product>
    fun findByTel(name: String): Array<Product>

    fun insert(product: Product): Int
    fun delete(product: Product): Int
    fun update(product: Product): Int
}