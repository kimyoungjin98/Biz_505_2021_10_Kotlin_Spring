// document.addEventListener("DOMContentLoaded", function() {
//    querySelector()
//    getElementById()
// })

// jQuery를 사용할 때
// $(document.ready(function() {   }))
// $(function() {  })

$(function() {
// jQuery 코드 영역
// $("table.buyer_list tr") -> 자체가 Selector()
// $("table.buyer_list tr").click(function() {   })
/*
    class가 buyer_list인 table 내( > )의 tr 중에 한 개가 클릭되면 함수를 실행하라
    data-id값을 getter하여 alert 창에 표시하라
    */
//    $("table.buyer_list tr").on("click", function() {
//        const id = $(this).data("id")
//        alert(id)
//        location.href = "/detail?userid" + id
//    })영진아... 안녕...? Hi Hi Young-Jin

    function tableClickHandler() {
    const id = $(this).data("id")
    location.href = "/detail?userid=" + id
    }
    $("table.buyer_list tr").on("click", tableClickHandler)
})