package com.example.memberfront.controller;

import com.example.memberfront.dto.Account;
import com.example.memberfront.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("account", new Account());
        return "join";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "join";
        }

        String username = memberService.createMember(account);
        log.info("joined username = " + username);
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modify(Model model, Principal principal) {
        Account account = this.memberService.readMember(principal.getName());
        model.addAttribute("account", account);
        return "modify";
    }

    @PostMapping("/modify")
    public String processModify(@ModelAttribute @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "modify";
        }

        String username = this.memberService.modifyMember(account);
        log.info("modified username = " + username);
        return "redirect:/";
    }
}
