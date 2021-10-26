$(function() {
    $("button.btn_save").on("click", function(){
        $("form").submit()
    })

    $("button.btn_list").on("click", function(){
        location.href = "/order/"
    })
})