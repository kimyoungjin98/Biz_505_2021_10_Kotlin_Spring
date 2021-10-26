$(function(){
    const button = $("section.order_detail button")
    if(button) {
        $(button).on("click", function(){
            const className = $(this).attr("class")
            // alert(className)

            let href = `${rootPath}order`

            if(className.includes("btn_update")) {
                 href = `${href}/update/${seq}`
            } else if(className.includes("btn_delete")) {
                href = `${href}/delete/${seq}`
                if(!confirm("삭제할까요?")) {
                    return false
                }
            } else if(className.includes("btn_list")) {
                href = `${href}/`
            }
             location.href = `${href}`

        })
    }
})