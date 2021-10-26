$(function() {
    // 저장버튼을 클릭했을 때
//    const btn_save = $("button.btn_save")
//    if(btn_save) {
//        $(btn_save).on("click", function(){
//            $("form").submit()
//        })
//    }

    // 따로 유효성 검사를 하지 않고 아래와 같이 코드를 적어도 무방하다
    $("button.btn_save").on("click", function(){
        $("form").submit()
    })
})