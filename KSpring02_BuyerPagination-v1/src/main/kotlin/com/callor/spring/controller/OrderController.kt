package com.callor.spring.controller

import com.callor.spring.ConfigData
import com.callor.spring.config.SalesDataInit
import com.callor.spring.model.Sales
import com.callor.spring.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.logging.Logger

@Controller
@RequestMapping(value = ["/order"])
class OrderController {

    val logger = LoggerFactory.getLogger(SalesDataInit::class.java)

    @Autowired
    private lateinit var oService: OrderService

    // localhost:8080/order/ 또는
    // localhost:8080/order 요청을 모두 수용
    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun list(model: Model): String {

        var salesList = oService.selectAll()
        model["SALES"] = salesList

        return "order/list"
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun insert(model:Model): String {
        val json = ConfigData.SALES_LIST[0]
        model["SALES"] = json

        return "order/write"
    }

    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(sales: Sales): String {
        oService.insert(sales)

        return "redirect:/order/"
    }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(model: Model, @RequestParam("seq") seq:Long): String {
        var salesList = oService.findById(seq)
        model["SALES"] = salesList

        return "order/detail"
    }

    @RequestMapping(value = ["/update/{seq}"], method = [RequestMethod.GET])
    fun update(model:Model, @PathVariable("seq") seq: Long):String {
        val sales = oService.findById(seq)
        model["SALES"] = sales

        return "order/write"
    }

    @RequestMapping(value = ["/update/{seq}"], method = [RequestMethod.POST])
    fun update(redirectAttributes: RedirectAttributes, sales:Sales, @PathVariable("seq") seq: Long):String {
        oService.update(sales)

        redirectAttributes["seq"] = sales.seq.toString()
        return "redirect:/order/detail"

    }

    @RequestMapping(value = ["/delete/{seq}"], method = [RequestMethod.GET])
    fun delete(@PathVariable("seq") seq: Long):String {
        oService.delete(seq)

        return "redirect:/order/"
    }
}