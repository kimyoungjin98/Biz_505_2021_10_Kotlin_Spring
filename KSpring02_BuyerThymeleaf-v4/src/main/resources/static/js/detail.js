$(function(){
    const button = $("section.buyer_detail button")
    if(button) {
        $(button).on("click", function(){
            const className = $(this).attr("class")
            // alert(className)

            let href = `${rootPath}buyer`

            if(className.includes("btn_update")) {
                // alert("수정" + userid)
                // alert(`Update  ${userid}`)

                href = `${href}/update/${userid}`
            } else if(className.includes("btn_delete")) {
                // alert("삭제" + userid)
                href = `${href}/delete/${userid}`
                if(!confirm("삭제할까요?")) {
                    return false
                }
            }
            location.href = `${href}`

        })
    }
})