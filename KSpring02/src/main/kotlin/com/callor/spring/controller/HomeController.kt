package com.callor.spring.controller

import com.callor.spring.model.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    @Autowired
    private lateinit var bService : BuyerService

    @ResponseBody
    @RequestMapping(value =["/"], method = [RequestMethod.GET])
    fun home():String{

        return "반갑습니다"
    }

    @RequestMapping(value=["/hello"], method = [RequestMethod.GET])
    fun hello(model:Model):String{

        model.addAttribute("name", "홍길동이")
        return "hello"
    }

    @ResponseBody
    @RequestMapping(value=["/list"], method = [RequestMethod.GET])
    fun list():Array<Buyer>{

        return bService.selectAll()
    }

    @ResponseBody
    @RequestMapping(value=["/getUser"], method = [RequestMethod.GET])
    fun getUser():Buyer{

        return bService.findById("user")
    }

}