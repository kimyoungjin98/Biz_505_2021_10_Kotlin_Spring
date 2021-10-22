package com.callor.spring.controller

import com.callor.spring.model.Sales
import com.callor.spring.service.OrderService
import org.hibernate.criterion.Order
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.lang.reflect.Method

@Controller
@RequestMapping(value = ["/order"])
class OrderController(val orService:OrderService) {

    // localhost:8080/order/
    // localhost:8080/order
    @RequestMapping(value = ["","/"], method = [RequestMethod.GET])
    fun list(model:Model):String {

        val salesList = orService.selectAll()
        model["SALES"] = salesList

        return "order/list"
    }

    @RequestMapping(value=["/detail"], method=[RequestMethod.GET])
    fun detail(model: Model, @RequestParam("seq") seq:Long ):String{

        var order = orService.findById(seq)
        model["ORDER"] = order

        return "order/detail"
    }

    @RequestMapping(value=["/delete"], method=[RequestMethod.GET])
    fun delete(@RequestParam("seq") seq:Long):String{
        orService.delete(seq)
        return "redirect:/order"
    }

    @RequestMapping(value=["/insert"], method = [RequestMethod.GET])
    fun insert(model: Model, order: Sales):String{

        model["ORDER"] = order

        return "order/write"
    }

    @RequestMapping(value=["insert"], method=[RequestMethod.POST])
    fun insert(order:Sales):String{
        orService.insert(order)
        return "redirect:/order"
    }

    @RequestMapping(value=["/update"], method = [RequestMethod.GET])
    fun update(@RequestParam("seq") seq: Long, model: Model):String{
        model["ORDER"] = orService.findById(seq)
        return "order/write"
    }

    @RequestMapping(value=["/update"], method = [RequestMethod.POST])
    fun update(order:Sales , redirectAttributes: RedirectAttributes):String{
        orService.update(order)
        redirectAttributes["seq"] = order.seq.toString()
        return "redirect:/order/detail"
    }


}