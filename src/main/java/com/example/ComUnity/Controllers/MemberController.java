package com.example.ComUnity.Controllers;


import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Service.MemberDetailsService;
import com.example.ComUnity.Setup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")
public class MemberController {

    @Autowired
    private Setup setup;

    @Autowired
    MemberDetailsService memberDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/{username}")
    public String showMember(@PathVariable String username, @AuthenticationPrincipal Member member, Model model)
    {

        boolean thisuser = username.equals(member.getUsername());

        Member thismember = memberDetailsService.getByUsername(username);

        model.addAttribute("thisuser", thisuser);

        model.addAttribute("member", thismember);

        return "member";
    }

}
