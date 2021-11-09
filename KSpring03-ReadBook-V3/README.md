# ReadBook Project V3 with Spring Security

* 2021-11-01
* 똑 소리나는 독서록 프로젝트에 Spring Security를 적용하여 로그인 기능 구현
* Spring Security를 적용하면 로그인, 로그인 후 Session 관리 등을 좀 더 편리하게 구현할 수 있다.  
Spring Security는 초기 설정이 다소 어렵고 불편하지만, 초기 설정이 잘 되면 상당 부분을 개발자가 직접 핸들링하지 않고도 인증, 인가, 권한 설정 등을 쉽게 할 수 있다.

## Security와 관련된 용어 정리
* 인증(Authentication): username, password를 사용하여 허가된 사용자 인가를 판별하는 것
* 인가(Authorization): 로그인 된 사용자에게 권한을 허락(부여)하는 것
* 권한 설정(Authority, Role): 인증받은 사용자의 인가 정보를 확인하여 접근할 수 있는 곳(페이지) 등을 확인하고 적절히 처리하는 것  
ADMIN, USER, GUEST 등의 역할을 부여하여 처리하는 것

* Ex) username: korea인 사용자가 로그인을 했다.  
  1. korea user가 정상적인 절차를 거쳐서 회원가입이 되고, 본인확인 절차를 통과했는가  
  자신의 mypage에 접근할 수 있는 권한 부여하기

  2. korea user에게 사전에 미리 부여된 역할이 무엇인가를 판단  
  USER(일반): mypage에서 자신의 정보를 보기, 수정, 삭제 등을 할 수 있게 한다.  
  ADMIN(관리자): 자신의 mypage에 접근할 수 있고다른 USER의 리스트를 보고 뭔가 실행할 수 있다.

---

# Spring Security를 사용한 Login 구현
* SecurityConfig(WebSecurityConfiguerAdapter 상속) 에 설정을 하여 login form을 custom할 수 있다.
* 이 때 login form의 method는 반드시 post로 action=${rootPath}/login으로 설정  
즉, security에서 제공하는 loginProcessor Url로 login 정보를 request하기  
Spring Security에서 기본으로 제공하는 login 기능을 사용하겠다 라는 의미
* 기본 login 기능은 username과  password 값을 받아서 인증 절차를 수행한다
* 만약 인증절차가 실패(username이 없거나, password가 틀리면)하면 무조건 원래의 login(/member/login)으로 redirect한다
* 이 때, error라는 매개변수(params)를 전달한다.
* thymeleaf로 만든 login form에서는 th:if="${param.login}" 코드를 사용하여 오류가 발생했음을 view에 보여줄 수 있다.

# Spring Security login LOGIC 호출
1. authenticated가 설정된 page에 접근(Request)
2. login 정보가 있는 지 확인
3. 없으면 loginPage()가 설정된 곳으로 redirect: login.html
4. 로그인 수행: ${rootPath}/login으로 POST 전송
5. config(auth: AuthenticationManaberBuilder)에 설정된 동작 수행
6. auth.userDetailsService(MemberLoginService()) 설정을 확인
   1. MemberLoginService() 클래스가 지정되어있다.
   2. UserDetailsService interface를 상속받어 작성된 클래스
   3. 이 클래스의 loadUserByUserName() method를 실행
   4. loadUserByName() method는 username을 기준으로 사용자 정보를 조회하여 MemberVO 객체 데이터를 만든다
   5. 그리고 configuer의 auth에게 MemberVO를 전달한다.

## login 수행 절차
1. localhost:8080/member/mypage에 접근하려고 시도
2. 순간 Controller로 전달되기 전에 Security의 filter가 가로채기
3. SecurityConfig.configuer(http) 함수를 참조하여
4. authenticated()가 설정되어 있는 지 확인
   1. 만약 Yes이면
   2. 기존에 로그인한 정보(httpSession)가 있는 지 내부적으로 검사
5. http.login().loginPage()에 설정된 Url로 redirect: No
6. MemberController.login()에 의해 member/login.html을 보여준다
7. username, password를 입력하고 Login 버튼 클릭
8. 보통은 form에서 데이터 입력하고 저장을 클릭하면 Controller의 POST method에 전달된다.
9. But, Spring-security가 적용된 프로젝트의 로그인은 다르다.
10. Spring Security가 제공하는 loginProcessor에게 전달된다
11. username을 UserDetailsService.loadUserByUserName()에 보내서 username에 해당하는 사용자 정보 추출하여 auth에게 전달
12. auth는 PasswordEncoder.matches()에게 비번 전달 후 비번이 일치하는 지 검사
13. 모두 일치하게 되면 사용자 정보를 session에 저장하고 로그인 절차가 완료된다.