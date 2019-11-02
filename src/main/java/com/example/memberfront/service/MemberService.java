package com.example.memberfront.service;

import com.example.memberfront.dto.Member;

public interface MemberService {
    Member readMember(String username);

    String createMember(Member member);

    String modifyMember(Member member);
}
