$(function(){



    $("nav li").on("click", function(){
        // this: JS에서 e.currentTarget
        const className = $(this).attr("class")
        const tagId = $(this).attr("id")
        const tagName = $(this).attr("name")
        const text = $(this).text()

        /*
        JS Debugging
        여러 개 변수 데이터를 alert()로 보고싶을 때 데이터들을 JSON 객체로 만들고
        JSON.stringify() 문자열로 바꿔서 alert()에 표시
        */
        const tagInfo = {
            tagName, tagId, className, text
        }

        // alert(JSON.stringify(tagInfo))

        // 방법 1
        /*
        $("li.login").on("click", function(){
            location.href = "/login"
        })
        $("li.order").on("click", function(){
            location.href = "/order"
        })
        $("li.pay").on("click", function(){
            location.href = "/pay"
        })
        $("li.home").on("click", function(){
            alert("Home")
            location.href = "/"
        })
        */

        // 방법 2
        /*
        if(text === "Home") {
            location.href = "/"
        } else if(text === "주문 관리") {
           location.href = "/order"
        } else if(text === "결제 관리") {
           location.href = "/pay"
        } else if(text === "로그인") {
           location.href = "/login"
        }
        */

        // 방법 3
        let href = "/"
        
        if(text === "Home") {
            href
        } else if(text === "주문 관리") {
            href += "order/"
        } else if(text === "결제 관리") {
           href += "pay"
        } else if(text === "로그인") {
           href += "login"
        }
        
        location.href = `${href}`
    })
})
