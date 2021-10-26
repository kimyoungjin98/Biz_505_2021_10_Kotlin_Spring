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

/**
 * Spring Boot가 시작될 때 사용할 초기값, 설정 등을 수행하는 클래스
 *
 * SalesRepository를 생성자 주입방식으로 Wiring한다
 *
 * 클래스의 생성자 method에 매개변수로 설정만 해두면 된다
 */
@SpringBootConfiguration
class SalesDataInit(val sDao: SalesRepository) {

    val logger = LoggerFactory.getLogger(SalesDataInit::class.java)

    private val pnames = listOf(
        "아이폰 13",
        "에어팟 프로",
        "애플 워치",
        "갤럭시 폴드",
        "갤럭시 버즈",
        "갤럭시 워치",
    )

    @Bean
    fun dataInit():CommandLineRunner {

        for (num in 0..100) {
            salesDataInit()
        }

        return CommandLineRunner {
            logger.debug("Sales 데이터 Complete!")
        }
    }

    private fun salesDataInit() {
        val userid = String.format("B%03d", ConfigData.RND.nextInt(20) + 1)
        val pname = pnames[ConfigData.RND.nextInt(pnames.size)]
        val qty = (ConfigData.RND.nextInt(10) + 10) * 10 // 100 ~ 190
        val price = (ConfigData.RND.nextInt(100) + 100) * 1000 // 100000 ~ 199000
        val df = SimpleDateFormat("yyyy-MM-dd")
        val dt = SimpleDateFormat("hh:mm:ss")

        val toDate = df.format(Date())
        val toTime = dt.format(Date())

        val sales = Sales(
            userid = userid,
            pname = pname,
            qty = qty,
            amt = price,
            total = qty * price,
            date = toDate,
            time = toTime,
        )

        sDao.save(sales)
    }

}
