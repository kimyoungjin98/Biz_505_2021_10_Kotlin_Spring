document.addEventListener("DOMContentLoaded", ()=>{
    const ul = document.querySelector("ul");

    const nav = (e)=> {
        if(e.target.tagName === "LI"){
            let path = "/readbook/";
            const text = e.target.textContent;
            if (text === "홈") {

            } else if (text === "내정보") {
                path += "member/mypage"
            } else if (text === "로그인") {
                path += "member/login"
            } else if (text === "로그아웃") {
                path += "logout"
            } else if (text === "작성") {
                path += "write"
            }
            location.href = path
        }
    }

    ul.addEventListener("click", nav);

    const div = document.querySelector(".nav_div")
    const li = document.createElement("li")
    fetch("http://localhost:8080/readbook/member/")
        .then((res) => res.text())
        .then((res) => {
            if(res == "NO"){
                li.innerText = "로그인"
            }else if(res == "OK"){
                li.innerText = "로그아웃"
            }else {
                console.log("엥?")
            }
            div.appendChild(li)
        })
})


