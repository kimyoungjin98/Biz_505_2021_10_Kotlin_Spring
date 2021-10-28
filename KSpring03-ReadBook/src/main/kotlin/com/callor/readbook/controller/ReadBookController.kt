package com.callor.readbook.controller

import com.callor.readbook.model.Book
import com.callor.readbook.model.ReadBook
import com.callor.readbook.service.impl.BookServiceImplV1
import com.callor.readbook.service.impl.ReadBookServiceImplV1
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = ["/read"])
class ReadBookController {

    val logger = LoggerFactory.getLogger(ReadBookController::class.java)

    @Autowired
    lateinit var bService:BookServiceImplV1

    @Autowired
    lateinit var rService:ReadBookServiceImplV1

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun insert():String {
        logger.debug("여기는 insert")
        return "write"
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(book: Book, readBook: ReadBook):String {
        bService.insert(book)
        rService.insert(readBook)

        logger.debug("여기는 insert-post")
        logger.debug("book에 들어가는 것은!!!: {}", book)
        logger.debug("readBook에 들어가는 것은!!!: {}", readBook)

        return "redirect:/"
    }

}