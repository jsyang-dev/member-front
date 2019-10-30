package com.example.memberfront.controller;

import com.example.memberfront.dto.Member;
import com.example.memberfront.service.MemberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new Member());
        return "member";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute Member member) {
        memberService.createMember(member);
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modify(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = this.memberService.readMember(userDetails.getUsername());
        model.addAttribute("user", member);
        return "member";
    }

    @PostMapping("/modify")
    public String processModify(@ModelAttribute Member member) {
        this.memberService.createMember(member);
        return "redirect:/";
    }
}
