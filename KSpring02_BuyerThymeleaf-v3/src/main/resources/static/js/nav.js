$(function(){

//    $("li.login").on("click", function(){
//
//    })


    $("nav li").on("click", function(){

        // this : e.currentTarget
        const className = $(this).attr("class")
        const tagId = $(this).attr("id")
        const tagName = $(this).attr("name")
        const text = $(this).text()

        const tagInfo = {
            tagName, tagId, className, text
        }

        alert(JSON.stringify(tagInfo))

        let href = "/"
        if(text === "결제관리"){
            href += "pay"
        } else if(text === "주문관리"){
            href += "order"
        } else if(text === "로그인"){
            href += "login"
        }
        location.href = href
    })

})