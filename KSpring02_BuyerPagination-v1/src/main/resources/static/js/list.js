$(function () {

    function tableClickHandler() {
        const id = $(this).data("id")

        // location.href = "/buyer/detail?userid=" + id
        location.href = `${rootPath}/buyer/detail?userid=${id}`

    }

    // 이미 생성된 DOM에는 정상적으로 event가 적용된다
    // 동적으로 추가된 append() DOM에는 event를 적용할 수 없다
    // $("table.buyer_list tr").on("click", tableClickHandler)

    // 동적으로 추가된 table tr 요소에 event를 설정할 때 사용
    $(document).on("click", "table.buyer_list tbody tr", tableClickHandler)
})