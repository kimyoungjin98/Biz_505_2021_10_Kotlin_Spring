$(function() {

//$("li.login").on("click",function(){
//    alert("로그인메뉴클릭")
//})
//$("li.pay").on("click",function(){
//    alert("결제관리")
//})
//$("li.home").on("click",function(){
//    alert("Home")
//})
//$("li.order").on("click",function(){
//    alert("주문관리")
//})

// 를 밑에 방법으로 한꺼번에 묶어서 사용하기

// nav안에 있는 li가 클릭이 되면
    $("nav li").on("click", function(){
    // this = e.currentTarget
        const className = $(this).attr("class") // className은 attribute(속성)을 이용해서 추출
        const tagId = $(this).attr("id") // id를 추출하려면 이렇게 사용한다
        const tagName = $(this).attr("name")
        const text = $(this).text()

/*
JS Debuging
여러개 변수 데이터를 alert()으로 보고 싶을 때
데이터들을 JSON 객체로 만들고
JSON.stringify() 문자열로 바꿔서 alert()에 표시하자
*/
        // 추출한 값들을 json타입으로 만들어서 내용확인해주기기
       const tagInfor = {
            tagName, tagId, className,text
        }
//
//        alert(JSON.stringify(tagInfor))

        let href="/"
        if(text === "Home") {
//            location.href="/"

        }else if(text === "주문 관리") {
//            location.href="/order"
        href+="order/"
        }else if(text === "결제 관리") {
            href+="pay"
        }else if(text === "로그인") {
            href+="login"
        }
        location.href=`${href}`
    })
})