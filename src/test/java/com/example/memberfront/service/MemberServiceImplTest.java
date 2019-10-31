package com.example.memberfront.service;

import com.example.memberfront.common.RestTemplateHelperImpl;
import com.example.memberfront.dto.Member;
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
    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private RestTemplateHelperImpl restTemplateHelper;

    @Test
    public void Should_정상_반환_When_회원_조회() {
        //given
        Member member = Member.builder()
                .username("user1")
                .password("1234")
                .name("아이유")
                .phone("01012345678")
                .email("user1@domain.com")
                .build();
        given(this.restTemplateHelper.getForEntity(eq(Member.class), any(String.class))).willReturn(member);

        //when
        final Member readMember = this.memberService.readMember(member.getUsername());

        //then
        assertThat(readMember.getUsername(), is(readMember.getUsername()));
        assertThat(readMember.getPassword(), is(readMember.getPassword()));
        assertThat(readMember.getName(), is(readMember.getName()));
        assertThat(readMember.getPhone(), is(readMember.getPhone()));
        assertThat(readMember.getEmail(), is(readMember.getEmail()));
    }
}