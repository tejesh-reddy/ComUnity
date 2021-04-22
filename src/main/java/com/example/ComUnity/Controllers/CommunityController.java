package com.example.ComUnity.Controllers;


import com.example.ComUnity.Domain.Access;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/com")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @GetMapping
    public String showAll(Model model)
    {
        model.addAttribute("communities", communityService.getAll());

        return "communities-all";
    }

    @GetMapping("/{name}")
    public String getCommunity(@PathVariable String name , @AuthenticationPrincipal Member member, Model model)
    {
        Community community = communityService.getByName(name);

        Access access = communityService.getAccess(name, member.getUsername());

        model.addAttribute("community", community);

        model.addAttribute("posts", communityService.getPosts(name));

        model.addAttribute("access", access);

        System.out.println("User: " + member + "\nAccess: " + access);

        return "show-community";
    }

    @GetMapping("/{name}/join")
    public String addToCommunity(@PathVariable String name, @AuthenticationPrincipal Member member)
    {

        communityService.addMember(name, member);

        System.out.println("redirect:/com/" + name);

        return "redirect:/com/" + name;
    }

}
