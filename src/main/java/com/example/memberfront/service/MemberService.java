package com.example.memberfront.service;

import com.example.memberfront.dto.Account;

public interface MemberService {
    Account readMember(String username);

    String createMember(Account account);

    String modifyMember(Account account);
}
