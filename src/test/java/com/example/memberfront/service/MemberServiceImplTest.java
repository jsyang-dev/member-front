package com.example.memberfront.service;

import com.example.memberfront.common.RestTemplateHelperImpl;
import com.example.memberfront.dto.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceImplTest {
    private Account account;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private RestTemplateHelperImpl restTemplateHelper;

    @Before
    public void setUp() {
        this.account = Account.builder()
                .username("user1")
                .password("1234")
                .name("아이유")
                .phone("01012345678")
                .email("user1@domain.com")
                .build();
    }

    @Test
    public void Should_정상_반환_When_회원_조회() {
        //given
        given(this.restTemplateHelper.getForEntity(eq(Account.class), any(String.class))).willReturn(this.account);

        //when
        final Account readAccount = this.memberService.readMember(this.account.getUsername());

        //then
        assertThat(readAccount.getUsername(), is(this.account.getUsername()));
        assertThat(readAccount.getPassword(), is(this.account.getPassword()));
        assertThat(readAccount.getName(), is(this.account.getName()));
        assertThat(readAccount.getPhone(), is(this.account.getPhone()));
        assertThat(readAccount.getEmail(), is(this.account.getEmail()));
    }

    @Test
    public void Should_아이디_반환_When_회원_저장() {
        //given
        given(this.restTemplateHelper.postForEntity(eq(String.class), any(String.class), eq(this.account))).willReturn(this.account.getUsername());

        //when
        final String username = this.memberService.createMember(this.account);

        //then
        assertThat(username, is(this.account.getUsername()));
    }

    @Test
    public void Should_아이디_반환_When_회원정보_수정() {
        //given
        given(this.restTemplateHelper.putForEntity(eq(String.class), any(String.class), eq(this.account))).willReturn(this.account.getUsername());

        //when
        final String username = this.memberService.modifyMember(this.account);

        //then
        assertThat(username, is(this.account.getUsername()));
    }
}