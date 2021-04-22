package com.example.ComUnity.Controllers;

import com.example.ComUnity.Dao.MemberDao;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Service.MemberDetailsService;
import com.example.ComUnity.Setup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EntryController {

    @Autowired
    private Setup setup;

    @Autowired
    MemberDetailsService memberDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String show(@AuthenticationPrincipal Member member, Model model)
    {

        model.addAttribute("member", member);

        model.addAttribute("posts", memberDetailsService.getPosts(member.getUsername()));

        return "home";
    }

    @GetMapping("/setup")
    public String setItUp(Model model)
    {
        setup.saveMembers(passwordEncoder);

        model.addAttribute("members", memberDetailsService.getAll());

        return "show-all";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}
