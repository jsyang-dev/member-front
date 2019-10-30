package com.example.memberfront.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {
    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    @Builder
    public Member(String username, String password, String name, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}