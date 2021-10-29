package com.callor.readbook.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Kotlin에서 사용되는 1급 함수
 * ( 1급 함수 : 클래스, 객체와 성질이 같은 함수 )
 *
 * 만약, logger() 함수를 호출하면 호출한 class(객체)를 함수에 전달하고 그 클래스를 getLogger() 함수에 매개변수로 부착하여
 * Logger 객체를 생성하여 return하는 함수
 */
inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)