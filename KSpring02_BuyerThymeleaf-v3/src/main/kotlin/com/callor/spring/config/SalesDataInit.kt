package com.callor.spring.config

import com.callor.spring.ConfigData
import com.callor.spring.model.Sales
import com.callor.spring.repository.SalesRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.crypto.Data

/**
 * Spring Boot가 시작될 때 사용할 초기값, 설정 등을 수행하는 클래스
 * SalesRepository를 생성자 주입방식으로 wirering한다
 * 클래스의 생성자 method에 매개변수로 설정만 해두면 된다.
 */
@SpringBootConfiguration
class SalesDataInit(val saleDao: SalesRepository) {
    // 생성자 주입방식, 클래스에 매개변수로 설정

    val logger = LoggerFactory.getLogger(SalesDataInit::class.java)
    // 가상데이터 만들어주기
    private val pnames = listOf(
        "아이폰13", "갤럭시폴드",
        "에어팟프로", "갤럭시버즈",
        "애플워치", "갤럭시 워치"
    )

    @Bean
    fun dataInit():CommandLineRunner {
        for(num in 1..100) {
            saleDataInit()
        }


        return CommandLineRunner {
            logger.debug("Sale 데이터 Complete!!")
        }

    }

    fun saleDataInit() {
        val userid = String.format("B%03d", ConfigData.RND.nextInt(20)+1)
        val pname = pnames[ConfigData.RND.nextInt(pnames.size)]

        // 10 ~ 19 까지의 범위에서 *10을하니 100~199까지의 범위가 됨
        val qty = ConfigData.RND.nextInt(10) + 10 * 10
        val price = ConfigData.RND.nextInt(100) + 100 * 1000

        val df = SimpleDateFormat("yyyy-MM-dd")
        val dt = SimpleDateFormat("hh:mm:ss")

        val toData = df.format(Date())
        val toTime = dt.format(Date())

        val sales = Sales(
            userid = userid,
            pname = pname,
            qty = qty,
            amt = price,
            total = qty * price,
            date = toData,
            time = toTime
        )

        saleDao.save(sales)
    }
}