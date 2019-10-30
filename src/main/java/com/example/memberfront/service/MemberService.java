package com.example.memberfront.service;

import com.example.memberfront.dto.Member;

public interface MemberService {
    void createMember(Member member);

    Member readMember(String username);
}
