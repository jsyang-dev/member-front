package com.example.memberfront.service;

import com.example.memberfront.dto.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    public CustomUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.readMember(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(member.getUsername())
                .password("{noop}" + member.getPassword())
                .roles("USER")
                .build();
    }
}
