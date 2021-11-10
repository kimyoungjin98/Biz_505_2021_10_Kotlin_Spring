package com.callor.readbook.controller

import com.callor.readbook.model.MemberVO
import com.callor.readbook.model.ReadBookDTO
import com.callor.readbook.service.ReadBookService
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.nio.file.attribute.UserPrincipal
import javax.servlet.http.HttpSession

@Controller
class BookController(val rbService:ReadBookService) {

    @RequestMapping(value=["/"], method = [RequestMethod.GET])
    fun home():String{

        return "redirect:/list"
    }

    @RequestMapping(value=["/write"], method = [RequestMethod.GET])
    fun write():String{

        return "/readBook/write"
    }

    @RequestMapping(value=["/write"], method = [RequestMethod.POST])
    fun write(readBook:ReadBookDTO):String{

        rbService.readBookInsert(readBook);

        return "/readBook/write"
    }

    @RequestMapping(value=["/list"])
    fun list(model:Model):String{

        model["LIST"] = rbService.readBookList()
        // model.addAttribute("LIST", rbService.readBookList())

        return "/readBook/list"
    }

}