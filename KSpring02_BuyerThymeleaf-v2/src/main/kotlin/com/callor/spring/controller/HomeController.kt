package com.callor.spring.controller

import com.callor.spring.ConfigString
import com.callor.spring.models.Buyer
import com.callor.spring.service.BuyerService
import com.callor.spring.service.impl.BuyerServiceImplV1
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    /**
     * lateinit var
     * 특별히 변수, 객체를 선언할 때 즉시 초기화 하지 않고
     * 나중에 초기화를 하겠다 라는 의미
     */
    @Autowired
    private lateinit var bService:BuyerService

    /**
     * Spring 일반 Controller 에서
     * method 가 문자열을 return 하면
     * view 파일을 열어서 rendering  하여 client 로 보내라
     *
     * RESTful API 방식이다
     * RestController 또는 method 에 @RespnsBody 가 부착되면
     * 문자열을 그대로 client 로 보내라
     */
    @RequestMapping(value=["/"],method=[RequestMethod.GET])
    fun home(model:Model) :String {

        println( ConfigString.APP_NAME )
        println( ConfigString.APP_VERSION )

        var userList = bService.selectAll()
        // model.addAttribute("USERS", userList)
        model["USERS"] = userList;

        return "home"
    }

    @ResponseBody
    @RequestMapping(value=["/list"], method = [RequestMethod.GET])
    fun list():Array<Buyer> {
        return bService.selectAll();
    }

    @RequestMapping(value=["/detail"], method = [RequestMethod.GET])
    fun detail( model: Model, @RequestParam("userid") userid:String ):String {

        val buyer = bService.findById(userid);
        model["BUYER"] = buyer

        return "detail"
    }

    @ResponseBody
    @RequestMapping(value=["insert"], method=[RequestMethod.GET])
    fun insert():Buyer{
        val insertBuyer = BuyerServiceImplV1.BUYER_LIST[0]
        bService.insert(insertBuyer)

        return insertBuyer
    }
}