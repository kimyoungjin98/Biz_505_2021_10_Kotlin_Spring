package com.callor.spring.config

import com.callor.spring.ConfigData
import com.callor.spring.model.Buyer
import com.callor.spring.repository.BuyerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional

// project가 시작될 때 무엇인가를 실행해죠!
@SpringBootConfiguration
class BuyerDataInit {

    private val logger = LoggerFactory.getLogger(BuyerDataInit::class.java)

    @Autowired
    private lateinit var bDao:BuyerRepository

    var names = listOf("노소연", "김영진", "고은결", "박은빈", "나나")

    fun initData(): CommandLineRunner {

        return CommandLineRunner {

            for (num in 1..100) {

                val userId = String.format("B%03d", num)

                val nums1 = ConfigData.RND.nextInt(names.size)
                val nums2 = ConfigData.RND.nextInt(names.size)
                val tel1 = ConfigData.RND.nextInt(1000) + 1
                val tel2 = ConfigData.RND.nextInt(1000) + 1
                val tel3 = ConfigData.RND.nextInt(1000) + 1
                val tel4 = ConfigData.RND.nextInt(1000) + 1

                val buyer = Buyer()

                buyer.userid = userId
                buyer.name = names[nums1]
                buyer.manager = names[nums2]
                buyer.tel = String.format("010-%04d-%04d", tel1, tel2)
                buyer.man_tel = String.format("010-%04d-%04d", tel3, tel4)

                logger.debug(buyer.toString())
                bDao.save(buyer)

            }
        }
    }
}