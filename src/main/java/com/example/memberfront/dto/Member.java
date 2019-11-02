package com.example.memberfront.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름를 입력해주세요.")
    private String name;

    @NotBlank(message = "핸드폰을 입력해주세요.")
    private String phone;

    @NotBlank(message = "이메일을 입력해주세요.")
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
