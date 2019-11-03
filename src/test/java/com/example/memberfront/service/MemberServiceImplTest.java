package com.example.memberfront.service;

import com.example.memberfront.common.RestTemplateHelperImpl;
import com.example.memberfront.dto.Member;
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
    private Member member;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private RestTemplateHelperImpl restTemplateHelper;

    @Before
    public void setUp() {
        this.member = Member.builder()
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
        given(this.restTemplateHelper.getForEntity(eq(Member.class), any(String.class))).willReturn(this.member);

        //when
        final Member readMember = this.memberService.readMember(this.member.getUsername());

        //then
        assertThat(readMember.getUsername(), is(this.member.getUsername()));
        assertThat(readMember.getPassword(), is(this.member.getPassword()));
        assertThat(readMember.getName(), is(this.member.getName()));
        assertThat(readMember.getPhone(), is(this.member.getPhone()));
        assertThat(readMember.getEmail(), is(this.member.getEmail()));
    }

    @Test
    public void Should_아이디_반환_When_회원_저장() {
        //given
        given(this.restTemplateHelper.postForEntity(eq(String.class), any(String.class), eq(this.member))).willReturn(this.member.getUsername());

        //when
        final String username = this.memberService.createMember(this.member);

        //then
        assertThat(username, is(this.member.getUsername()));
    }

    @Test
    public void Should_아이디_반환_When_회원정보_수정() {
        //given
        given(this.restTemplateHelper.putForEntity(eq(String.class), any(String.class), eq(this.member))).willReturn(this.member.getUsername());

        //when
        final String username = this.memberService.modifyMember(this.member);

        //then
        assertThat(username, is(this.member.getUsername()));
    }
}