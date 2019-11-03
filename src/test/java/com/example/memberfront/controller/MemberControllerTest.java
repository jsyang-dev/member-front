package com.example.memberfront.controller;

import com.example.memberfront.dto.Member;
import com.example.memberfront.service.MemberServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberServiceImpl memberService;

    @Test
    public void Should_정상_리턴_When_회원가입_페이지_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(get("/member/join"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("join"))
                .andExpect(model().attribute("member", instanceOf(Member.class)))
                .andExpect(content().string(containsString("_csrf")));
    }

    @Test
    public void Should_리다이렉트_When_회원가입_POST_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(post("/member/join")
                .param("username", "user1")
                .param("password", "1234")
                .param("name", "아이유")
                .param("phone", "01012345678")
                .param("email", "user1@domain.com")
                .with(csrf()));

        //then
        actions.andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void Should_에러메시지_표시_When_파라미터없이_회원가입_POST_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(post("/member/join")
                .with(csrf()));

        //then
        actions.andDo(print())
                .andExpect(view().name("join"))
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("_csrf")))
                .andExpect(content().string(containsString("error-message")));
    }

    @Test
    @WithMockUser
    public void Should_정상_리턴_회원정보수정_페이지_호출() throws Exception {
        //given
        Member member = Member.builder()
                .username("user")
                .password("1234")
                .name("아이유")
                .phone("01012345678")
                .email("user1@domain.com")
                .build();
        given(this.memberService.readMember("user")).willReturn(member);

        //when
        final ResultActions actions = this.mockMvc.perform(get("/member/modify"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("modify"))
                .andExpect(model().attribute("member", instanceOf(Member.class)))
                .andExpect(content().string(containsString("_csrf")))
                .andExpect(content().string(containsString("value=\"user\"")))
                .andExpect(content().string(containsString("value=\"아이유\"")))
                .andExpect(content().string(containsString("value=\"01012345678\"")))
                .andExpect(content().string(containsString("value=\"user1@domain.com\"")));
    }

    @Test
    @WithMockUser
    public void Should_리다이렉트_When_회원정보수정_POST_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(post("/member/modify")
                .param("username", "user")
                .param("password", "1234")
                .param("name", "아이유")
                .param("phone", "01012345678")
                .param("email", "user1@domain.com")
                .with(csrf()));

        //then
        actions.andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser
    public void Should_에러메시지_표시_When_파라미터없이_회원정보수정_POST_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(post("/member/modify")
                .with(csrf()));

        //then
        actions.andDo(print())
                .andExpect(view().name("modify"))
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("_csrf")))
                .andExpect(content().string(containsString("error-message")));
    }
}
