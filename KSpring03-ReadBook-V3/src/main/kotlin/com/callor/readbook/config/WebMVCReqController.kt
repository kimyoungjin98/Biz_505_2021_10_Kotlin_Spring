package com.callor.readbook.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Controller를 만들지 않고 MVC 패턴의 가상의 Request를 처리하는 클래스
 */
@SpringBootConfiguration
class WebMVCReqController:WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry
            // member/mypage 요청
            .addViewController("/member/mypage")
            // mypage.html return
            .setViewName("member/mypage")
    }
}