package com.callor.spring

import com.callor.spring.model.Buyer
import kotlin.random.Random

class ConfigData {

    // public static final String APP_NAME = "나라상사 고객관리"
    //      System.out.println(ConfigString.APP_NAME)
    // java static 키워드와 유사한 역할을 하는 객체
    companion object {
        val APP_NAME = "나라상사 고객관리"
        val APP_VERSION = "2021 V2"

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
}