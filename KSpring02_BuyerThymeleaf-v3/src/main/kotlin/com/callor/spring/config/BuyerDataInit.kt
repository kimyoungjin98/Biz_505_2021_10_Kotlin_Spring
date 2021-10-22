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

// project가 시작될 때 무엇인가 실행해달라
@SpringBootConfiguration

class BuyerDataInit {

    private val logger = LoggerFactory.getLogger(BuyerDataInit::class.java)

    // setter 주입방식
    // 서비스를 건너뛰고 바로 Dao를 불러오게 하기
    @Autowired
    private lateinit var bDao : BuyerRepository

    var names = listOf("노소연","고은결","김영진","박은빈","나나")

    @Bean
    @Transactional
    fun initData():CommandLineRunner {

        return CommandLineRunner {

            for (num in 1..100) {

                var userId = String.format("B%03d", num)
                val nums1 = ConfigData.RND.nextInt(names.size)
                val nums2 = ConfigData.RND.nextInt(names.size)
                val tel1 = ConfigData.RND.nextInt(1000) + 1
                val tel2 = ConfigData.RND.nextInt(1000) + 1
                val tel3 = ConfigData.RND.nextInt(1000) + 1
                val tel4 = ConfigData.RND.nextInt(1000) + 1

                // 빈배열 생성
                val buyer = Buyer()

                buyer.userid = userId
                buyer.name = names[nums1]
                buyer.manager = names[nums2]

                buyer.tel = String.format("010-%04d-%04d", tel1, tel2)

                // man전화번호 만들기
                buyer.man_tel = String.format("010-%04d-%04d", tel3, tel4)

                logger.debug(buyer.toString())
                // 임의 데이터 DB에 넣어주기
                bDao.save(buyer)
            }
        }
    }

}