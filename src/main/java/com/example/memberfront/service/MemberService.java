package com.example.memberfront.service;

import com.example.memberfront.dto.Member;

public interface MemberService {
    Member readMember(String username);

    void createMember(Member member);

    void modifyMember(Member member);
}
