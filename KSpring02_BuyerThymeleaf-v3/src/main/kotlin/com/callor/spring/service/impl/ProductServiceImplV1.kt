package com.callor.spring.service.impl

import com.callor.spring.model.Buyer
import com.callor.spring.model.Product
import com.callor.spring.service.ProductService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ProductServiceImplV1 : ProductService {

    private companion object {
        val RND = Random(System.currentTimeMillis())
        val PRODUCT_LIST = arrayOf(
            Product(
                p_id = "P001",
                p_no = 1,
                p_name = "아이폰 13",
                p_price = "1,200,000",
                p_qty = 10,
                total_price = "12,000,000"
            ),
            Product(
                p_id = "P002",
                p_no = 2,
                p_name = "갤럭시 zFlip3",
                p_price = "1,500,000",
                p_qty = 10,
                total_price = "15,000,000"
            )
        )
    }

    override fun selectAll(): Array<Product> {
        return PRODUCT_LIST
    }

    override fun findById(p_id: String): Product {
        val findUser = PRODUCT_LIST.filter { product -> product.p_id == p_id }
        return findUser[0]
    }

    override fun findByName(name: String): Array<Product> {
        val userNum = RND.nextInt(PRODUCT_LIST.size)
        return arrayOf(PRODUCT_LIST[userNum])
    }

    override fun findByTel(name: String): Array<Product> {
        TODO("Not yet implemented")
    }

    override fun insert(product: Product): Int {
        TODO("Not yet implemented")
    }

    override fun delete(product: Product): Int {
        TODO("Not yet implemented")
    }

    override fun update(product: Product): Int {
        TODO("Not yet implemented")
    }
}