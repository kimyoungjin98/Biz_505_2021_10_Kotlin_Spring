$(function(){

    const btn_save = $("button.btn_save")
    if(btn_save){
        $(btn_save).on("click", function(){
            $("form").submit()
        })
    }

})