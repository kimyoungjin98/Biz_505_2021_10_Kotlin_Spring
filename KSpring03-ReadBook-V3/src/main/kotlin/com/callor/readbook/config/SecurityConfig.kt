package com.callor.readbook.config

import com.callor.readbook.service.MemberLoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.annotation.WebServlet

// 프로젝트 설정을 위한 Class
@SpringBootConfiguration
// 프로젝트에 Spring Security 설정을 추가한다
// Spring Security를 Customizing 하겠다
 @EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    //    @Autowired
    //    lateinit var mService:MemberLoginService

    // 이러한 패턴으로 Request가 오면 건들지 말고 ignoring(무시)해라
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            // static 폴더 안에 들어있는 파일들만
            // "/static/*",

            // static 폴더 안에 있는 폴더와 파일들 모두
            "/static/**",
            "/static/css/**",
            "/static/js/**",
            "/static/images/**",
            // "*.icon", (icon이 아닌가...?)
            "*.ico",
            "*.txt"
        )

        // application.yml에 static으로 설정해놓은 `static-locations` 부분을 불러와서 자동으로 ignoring 하는 코드
        // 하지만, 한 번씩 제대로 작동되지 않는 경우가 있어서 위처럼 적어놓음.
            // 무시한다 (3)
        web.ignoring().requestMatchers(
            PathRequest
                // staticResource 안에 있는 (1)
                .toStaticResources()
                // location은 (2)
                .atCommonLocations()
        )
    }

    /*
    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
    }
    */

    /**
     * 인증절차를 수행하는 policy 설정
     */
    override fun configure(http: HttpSecurity) {

//        http.csrf().disable();


//        http
//            // 인증, 인가를 허용하겠다.
//            // client로부터 전달된 Request가 인가된 요청인가를 확인하겠다
//            .authorizeRequests()
//            // member/mypage라는 요청에 인증을 받도록 한다.
//            .antMatchers("/member/mypage").authenticated()
//            // 모든 요청에 대하여 인가를 허용한다.
//            .antMatchers("/**").permitAll()

        /**
         * antMatchers(), mvcMatchers() 등은 authorizeRequests() 함수와 chaining 관계에 있는 함수들이다
         *
         * authorizeRequests() 함수 아래에 다수의 antMatchers()를 계속해서 추가할 수 있다.
         */

        http.authorizeRequests()
            .antMatchers("/member/login").permitAll()
            /*
             83 & 85번 줄의 코드 순서대로 한다면

             member/mypage는 인증을 하게 하되
             그 외의 모든 폴더 및 파일들은 그냥 허용해라

             ( 순서가 바뀌면 완전 다른 코드가 나와버린다.. 순서가 바뀌면 의미가 없는 코드가 되어버리는 거 같다.. )
             */
            // member/mypage로 Request가 오면 인증 절차를 수행하라
            .antMatchers("/member/mypage", "/", "/write").authenticated()
            // member 폴더에 있는 모든 폴더와 파일들을 허용하라
            .antMatchers("/member/**").permitAll()
            .antMatchers("/**").permitAll()

        // 단독으로 사용되는 method 함수들은 http.함수() 형식으로 사용
        // popup 로그인
//        http.httpBasic()
        // 로그인 form 보이기
//        http.formLogin()

        // 단독으로 사용되는 method 함수들을 chaining 방식으로 사용할 때는 and() 함수로 연결해준다.
//            .and().httpBasic()
        // Custom되어있는 login form을 사용할 때는    .and()로 연결하지 말 것.!
        http.formLogin()
            // Spring Security에서 기본적으로 적용되어있는 login form이 아닌 내가 만든 login 페이지로 가겠다!!
            // Security 기ㅜ본 form 화면을 보이는 대신 내가 만든 MemberController의 login으로 redirect하라
            .loginPage("/member/login").permitAll()
            // Custom Login form에 action과 같은 Url 지정
            .loginProcessingUrl("/login")
//            .usernameParameter("userid")
//            .passwordParameter("password")

        http.logout()
            // logoutRequestMatcher에게 logout이 요청이 되면 "/"으로 이동하라
            // {rootPath}/logout으로 요청이 들어오면 logout을 수행하라
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            // logout이 정상적으로 수행되면 /member/mypage로 redirect를 수행하라
            .logoutSuccessUrl("/")
    }

    /**
     * {noop}
     * Spring Security에서 제공하는 password 정책
     *
     * 아직 암호화를 구현하지 않은 상태에서 테스트를 하기 위해서 실행하는 코드
     *
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        /*
        auth
            .inMemoryAuthentication()

            // 아래와 같은 ID, PASSWORD를 입력했을 때
            .withUser("inizz")
            .password("{noop}12345")

            // USER, ADMIN이라는 권한을 부여한다.
            .roles("USER", "ADMIN")
         */

        // Security에 UserDetailService interface를 상속받은 MemberLoginService 클래스의 객체를 건네주면
        //  회원 정보 인증 할 때 이를 사용한다.
        //MemberLoginService.loadUserByName() 함수를 실행하여 사용자 정보를 달라!
        auth.userDetailsService(MemberLoginService())
            // auth에 담긴 사용자 정보에서 password 항목을 찾아서
            //  CustomPasswordEncoder()에게 보내서 비밀번호가 맞는 지 검사하라
            .passwordEncoder(CustomPasswordEncoder())
    }
}

class CustomPasswordEncoder:PasswordEncoder {
    override fun encode(plan: CharSequence): String {
        return plan.toString()
    }

    override fun matches(plan: CharSequence?, crypt: String?): Boolean {
        return plan == crypt
    }
}