package com.example.memberfront.service;

import com.example.memberfront.dto.Account;
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
        Account account = memberService.readMember(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password("{noop}" + account.getPassword())
                .roles("USER")
                .build();
    }
}
