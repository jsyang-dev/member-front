package com.example.memberfront.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Should_정상_리턴_When_미로그인상태_메인_페이지_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(get("/"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("isLogin", "username"))
                .andExpect(model().attribute("isLogin", false))
                .andExpect(model().attribute("username", "not logged in"));
    }

    @Test
    @WithMockUser
    public void Should_정상_리턴_When_로그인상태_메인_페이지_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(get("/"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("isLogin", "username"))
                .andExpect(model().attribute("isLogin", true))
                .andExpect(model().attribute("username", "user"));
    }

    @Test
    public void Should_정상_리턴_When_로그인_페이지_호출() throws Exception {
        //when
        final ResultActions actions = this.mockMvc.perform(get("/login"));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(containsString("_csrf")));
    }
}