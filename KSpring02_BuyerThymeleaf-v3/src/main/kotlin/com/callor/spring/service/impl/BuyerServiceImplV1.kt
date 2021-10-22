package com.callor.spring.service.impl

import com.callor.spring.ConfigData
import com.callor.spring.model.Buyer
import com.callor.spring.repository.BuyerRepository
import com.callor.spring.service.BuyerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

/**
 * 클래스의 매개변수를 사용하여 생성자 주입하기
 *
 * class 클래스(주입받을 객체, 변수선언)
 */

@Service("bServiceV1")
class BuyerServiceImplV1(val bRepo:BuyerRepository) : BuyerService {

    // setter 주입으로 와이어링 하기
    @Autowired
    private lateinit var bDao : BuyerRepository

    // 현재 BuyerServiceImplV1에서 사용할 가상 데이터를 선언하기
    // private: 여기 class 내부에서만 사용하는 static 변수 선언하기


    override fun selectAll(): Array<Buyer> {
//        return ConfigData.BUYER_LIST
        // 전체를 가져와서 타입이 틀린것을 Array 로 타입변경??
        return bRepo.findAll().toTypedArray()
    }

    override fun findById(userid: String): Buyer {
//        val findUser = ConfigData.BUYER_LIST.filter { buyer -> buyer.userid == userid }
        // repository 의 findById() 는 실제 데이터 (Buyer)를 optional 이라는 특별한 객체로
        // wrapping 하여 가져온다.
        // 필요한 데이터는 .get() method 를 사용하여
        // 한번 더 추출해 주어야 한다.
        val buyer:Optional<Buyer> = bRepo.findById(userid)
        return buyer.get()
//        return findUser[0]
    }

    override fun findByName(name: String): Array<Buyer> {
//        val userNum = ConfigData.RND.nextInt(ConfigData.BUYER_LIST.size)
//        val buyers = bRepo.findByName(name)
//        return buyers

        // 별도의 값이 없다면 바로 return하는 것을 권장
        return bRepo.findByName(name)
//        return arrayOf(ConfigData.BUYER_LIST[userNum])    }
    }
    override fun findByTel(name: String): Array<Buyer> {
        TODO("Not yet implemented")
    }

    override fun insert(buyer: Buyer): Buyer {

//        val resultBuyer = bDao.save(buyer)
//        return resultBuyer
        // Insert Or Update
        // 기존데이터가 있으면 update, 없으면 insert
        return bRepo.save(buyer)

    }

    override fun delete(userid: String) {



        return bRepo.deleteById(userid)
    }

    override fun update(buyer: Buyer): Buyer {
        return bRepo.save(buyer)
    }

}