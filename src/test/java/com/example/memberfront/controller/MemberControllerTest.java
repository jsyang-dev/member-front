package com.example.memberfront.controller;

import com.example.memberfront.annotation.WithUser;
import com.example.memberfront.dto.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithUser
    public void join() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(get("/member/join"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("member"))
                .andExpect(model().attributeExists("member", "title"))
                .andExpect(model().attribute("member", instanceOf(Member.class)))
                .andExpect(model().attribute("title", "회원가입"))
                .andExpect(content().string(containsString("_csrf")));
    }
}
