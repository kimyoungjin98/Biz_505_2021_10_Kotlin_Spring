package com.callor.readbook.controller

import com.callor.readbook.model.ReadBookDTO
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class BookController(val rbService:ReadBookService) {

    @RequestMapping(value=["/write"], method = [RequestMethod.GET])
    fun write():String{

        return "/readbook/write"
    }

    @RequestMapping(value=["/write"], method = [RequestMethod.POST])
    fun write(readBook:ReadBookDTO):String{

        rbService.readBookInsert(readBook);

        return "/readbook/write"
    }

}