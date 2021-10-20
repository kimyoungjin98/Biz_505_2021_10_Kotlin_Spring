package com.callor.spring.controller

import com.callor.spring.ConfigString
import com.callor.spring.models.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value=["/buyer"])
class BuyerController(val bService:BuyerService) {

     @RequestMapping(value=["/list"], method = [RequestMethod.GET])
    // @GetMapping(name="/list")
    fun list(model:Model):String{
        val buyerList = bService.selectAll()
        model["BUYERS"] = buyerList
        return "buyer/list"
    }

    @RequestMapping(value=["/detail"],method=[RequestMethod.GET])
    fun detail( model:Model, @RequestParam("userid") userid:String):String {

        val buyer = bService.findById(userid)
        model["BUYER"] = buyer
        return "buyer/detail" // detail.html 을 열어라

    }

    @ResponseBody
    @RequestMapping(value=["/insert"],method=[RequestMethod.GET])
    fun insert(): String {
        val insertBuyer = ConfigString.BUYER_LIST[0]
        bService.insert(insertBuyer)
        return "buyer/write"
    }

//    @RequestMapping(value=["/write"], method = [RequestMethod.GET])
//    fun write():String{
//
//        return "buyer/write"
//    }


}