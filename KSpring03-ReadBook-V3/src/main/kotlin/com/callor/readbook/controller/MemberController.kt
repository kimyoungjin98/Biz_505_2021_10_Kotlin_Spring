package com.callor.readbook.controller

import com.callor.readbook.service.MemberLoginService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = ["/member"])
class MemberController(val mService:MemberLoginService) {

    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun login():String {
        return "member/login"
    }

    @ResponseBody
    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun fetch(@AuthenticationPrincipal userAccount:UserDetails):String {

        if(userAccount != null) {
            return "OK"
        } else {
            return "FAIL"
        }
//        } else if(userAccount == null){
//            return "NO"
//        } else{
//            return "FAIL"
//        }

    }

    @RequestMapping(value = ["/mypage"], method = [RequestMethod.GET])
    fun mypage(@AuthenticationPrincipal userDetails: UserDetails, model:Model):String {

        val username = userDetails.username;
        model["USER"] = mService.findById(username)

        return "member/mypage"
    }

}