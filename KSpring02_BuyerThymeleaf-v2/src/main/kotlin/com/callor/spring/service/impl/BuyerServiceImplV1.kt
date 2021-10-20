package com.callor.spring.service.impl

import com.callor.spring.models.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service("bServiceV1")
class BuyerServiceImplV1:BuyerService {

    private lateinit var bDao : BuyerRepository

    // 현재 BuyerServiceImplV1 에서 사용할 가상 데이터를
    // 선언하기
    companion object {
        val RND = Random(System.currentTimeMillis())
        val BUYER_LIST = arrayOf(
            Buyer(
                userid = "B001",
                name = "Hong",
                address = "Seoul",
                tel = "010-1234-5786",
                manager = "Lee",
                man_tel = "010-111-2222",
                buy_total = 10000
            ),
            Buyer(
                userid = "B002",
                name = "Na",
                address = "Namwon",
                tel = "019-124-5996",
                manager = "Wolmae",
                man_tel = "010-2222-6568",
                buy_total = 15000
            ),
            Buyer(
                userid = "B003",
                name = "Jang",
                address = "Haenam",
                tel = "019-126-5776",
                manager = "Sinla",
                man_tel = "010-7878-1111",
                buy_total = 20000
            )
        )
    }

    override fun selectAll(): Array<Buyer> {
        return BUYER_LIST
    }

    override fun findById(userid: String): Buyer {
        val findUser = BUYER_LIST.filter { buyer -> buyer.userid == userid }
        return findUser[0]
    }

    override fun findByName(name: String): Array<Buyer> {
        TODO("Not yet implemented")
    }

    override fun findByTel(name: String): Array<Buyer> {
        TODO("Not yet implemented")
    }

    override fun insert(buyer: Buyer): Buyer {

        return bDao.save(buyer)
    }

    override fun delete(buyer: String): Buyer {
        TODO("Not yet implemented")
    }

    override fun update(buyer: Buyer): Buyer {
        TODO("Not yet implemented")
    }

}