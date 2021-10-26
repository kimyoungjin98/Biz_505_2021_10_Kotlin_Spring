package com.callor.spring.controller

import com.callor.spring.ConfigData
import com.callor.spring.config.logger
import com.callor.spring.model.Buyer
import com.callor.spring.service.BuyerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * 2021-10-26
 * pagination 을 적용하기위하여 controller method 를 변경한다.
 * 전체리스트를 보여주는 list() 함수를 변경한다.
 *  가. list() 함수에 pagination 을 적용하기 위한 매개변수를 설정한다.
 */
@Controller
@RequestMapping(value = ["/buyer"])
class BuyerController(val bService: BuyerService) {

    // private val logger = LoggerFactory.getLogger(BuyerController::class.java)

    /**
     * list() 함수에 Pageable 클래스 객체를 매개변수로 선언
     * Pageable pageable
     * JPA의 기능을 사용하여 Pagination을 구현하기 위한 하나의 도구
     *
     * http://localhost:8080/erp/buyer/list
     *      ?page=10
     *      &size=10
     *      &sort=userid,desc
     *      &sort=name,asc
     *
     * 만약 주문정보를 조회한다면 다음처럼 최근 입력한 자료가 먼저 나타나도록 할 때
     * http://localhost:8080/erp/buyer/list
     *      ?page=10
     *      &size=10
     *      &sort=date,desc
     *      &sort=time,asc
     */
    // @GetMapping(name = ["/list"])
    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list(model: Model, pageable: Pageable): String {

        logger().debug("여기는 list 함수")
        logger().debug("Pageable {}", pageable.toString())

        val buyerList = bService.selectAll(pageable)
        model["BUYERS"] = buyerList

        return "buyer/list"
    }

    /*
    @ResponseBody
    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun list(model: Model, pageable: Pageable): Page<Buyer> {

        logger().debug("fun list()가 여기다!")
        logger().debug("Pageable {}", pageable.toString())

        val buyerList = bService.selectAll(pageable)
        model["BUYERS"] = buyerList

        // return "buyer/list"
        return buyerList
    }
    */

    @RequestMapping(value = ["/sub_page"], method = [RequestMethod.GET])
    fun subPage(model: Model, pageable: Pageable): String {

        val buyerList = bService.selectAll(pageable)
        model["BUYERS"] = buyerList

        return "buyer/sub_page"
    }

    @RequestMapping(value = ["/list/{page}"], method = [RequestMethod.GET])
    fun list(model: Model, @PathVariable("page") page: String = "0"): String {

        val intPage = try {
            page.toInt()
        } catch (e: Exception) {
            logger().debug("매개변수 오류!")
            // 오류가 나면 intPage를 0으로 만들어라
            0
        }

        logger().debug("여기는 Page List 함수")

        val buyerList = bService.selectWithPageable(intPage)
        model["BUYERS"] = buyerList

        return "buyer/list"
    }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(model: Model, @RequestParam("userid") userid: String): String {
        var userInfo = bService.findById(userid)
        model["BUYER"] = userInfo
//        println(userInfo.toString())

        return "buyer/detail"
    }

//    @ResponseBody
//    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
//    fun insert(): Buyer {
//
//        val insertBuyer = ConfigData.BUYER_LIST[0]
//        bService.insert(insertBuyer)
//
//        return insertBuyer
//    }

    /**
     * ModelAndAttribute 속성, 기능
     * Controller에서 Model 객체를 담고 form 화면을 rendering하면
     * 보통은 form의 value 속성에 하나하나 데이터를 추가하여 작성을 한다
     *
     * ModelAndAttribute를 사용하면 각각 view template의 고유 기능을 사용하여
     * id, name, value 값을 자동으로 채워넣는 기능을 만들 수 있다.
     *
     * thymeleaf template를 사용할 때는 form tag의 model에 담긴 object를 지정해주고
     * 각 input box에서는 field 속성으로 해당 멤버변수(요소, 속성)을 지정해주면
     * template 엔진이 rendering을 수행하면서 input에 필요한 요소들을 적절하게 생성해준다
     */

    /*
    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun write(model: Model): String {
        val insertBuyer = ConfigData.BUYER_LIST[0]
        model["BUYER"] = insertBuyer

        return "buyer/write"
    }
    */

    @RequestMapping(value = ["/insert"], method = [RequestMethod.GET])
    fun write(model: Model): String {

        val buyer:Buyer = bService.insert()
        model["BUYER"] = buyer

        return "buyer/write"
    }


    @RequestMapping(value = ["/insert"], method = [RequestMethod.POST])
    fun insert(model: Model, buyer: Buyer): String {
        bService.insert(buyer)

        return "redirect:/buyer/list"
    }

    // localhost:8080/update/B001 URL로 요청을 하면
    // B001 값을 userid에 담아서 함수 내부로 전달한다
    @RequestMapping(value = ["/update/{userid}"], method = [RequestMethod.GET])
    fun update(model: Model, @PathVariable("userid") userid: String): String {
        val buyer = bService.findById(userid)
        model["BUYER"] = buyer
        return "buyer/write"
    }

    /**
     * update를 실행할 때
     *      localhost:8080/buyer/update/userid 값으로 URL이 구성되어있음
     * update 화면에서 저장을 누르면 원래 요청했던 주소가 action이 되어 요청되므로
     * 여기에서는 userid가 필요없지만 pathVariable로 설정해주어야 한다
     */
    @RequestMapping(value = ["/update/{userid}"], method = [RequestMethod.POST])
    fun update(redirectAttributes: RedirectAttributes, buyer: Buyer, @PathVariable("userid") userid: String): String {
        bService.update(buyer)

        // model + return 주소 =>
        // localhost:8080/buyer/detail?userid=ㅇㅇㅇ 형식으로
        // redirect 주소가 만들어진다
        // model["userid"] = buyer.userid.toString()

        /**
         * Spring MVC에서는 model에 변수를 담으면 redirect를 실행할 때 model에 담긴 변수를
         *  queryString으로 부착하여 전송을 한다
         *
         * 이 기능이 boot에서는 금지되기에
         *  같은 기능을 구현하기 위하여 model 대신 RedirectAttributes를 사용
         */
        // 단 return이 redirect일 경우만 해당
        redirectAttributes["userid"] = buyer.userid.toString()
        return "redirect:/buyer/detail"

        // redirectAttributes를 사용하지 않으면 다음과 같이 작성해야 한다.
        // return "redirect:/buyer/detail?userid=" + buyer.userid.toString()
    }

    @RequestMapping(value = ["/delete/{userid}"], method = [RequestMethod.GET])
    fun delete(buyer: Buyer, @PathVariable("userid") userid: String): String {
        bService.delete(userid)

        return "redirect:/buyer/list"
    }

}