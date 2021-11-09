package com.callor.readbook.service

import com.callor.readbook.model.MemberVO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Security login Service Class
 *  (*UserDetailsService)
 *
 * Security login Service UserDetailsService 를 상속
 */
@Service
class MemberLoginService:UserDetailsService {

    // 가상의 memberList 생성
    private val userList = listOf(
        MemberVO(username = "in", password = "12345"),
        MemberVO(username = "iniz", password = "12345"),
        MemberVO(username = "inizz", password = "12345"),
    )

    // 사용자 또는 사용자 ID를 검색해서 만약 결과가 있으면 그 정보를 return해라
    // findByUserName(username): UserDetails
    override fun loadUserByUsername(username: String): UserDetails {
        // 배열.find {}: 배열의 요소 중 원하는 값이 담겨있는가?
        // 담겨있으면 해당 값을 return, 없으면 null return
        val member:UserDetails? = userList.find { it.username == username }
        /*
        if(member == null) {
        // member가 없으면..
            throw UsernameNotFoundException("사용자 ID가 잘못되었습니다.")
        }
        */
        member ?: throw UsernameNotFoundException("사용자 ID가 잘못되었습니다.")

        return member
    }

}