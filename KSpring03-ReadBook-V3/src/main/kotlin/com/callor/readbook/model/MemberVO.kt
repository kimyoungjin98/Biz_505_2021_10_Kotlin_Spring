package com.callor.readbook.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

// Spring Security 에서 login 한 사용자(User, Member)를 담아둘 VO 클래스
data class MemberVO(
    private var username:String,
    private var password:String,
    private var enabled:Boolean = true,
    private var credentialsNonExpired:Boolean = true,
    private var accountNonExpired: Boolean = true,
    private var accountNonLocked:Boolean = true,
    private var authorities:Collection<GrantedAuthority> = setOf()
):UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = password

    override fun isEnabled(): Boolean = enabled
    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired
    override fun isAccountNonExpired():Boolean = accountNonExpired
    override fun isAccountNonLocked(): Boolean = accountNonLocked

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities
    fun setAuthorities(authorities: Collection<GrantedAuthority>) {
        this.authorities = authorities
    }
    
}
