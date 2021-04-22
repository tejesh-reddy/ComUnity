package com.example.ComUnity.Controllers;


import com.example.ComUnity.DTO.PostForm;
import com.example.ComUnity.Domain.Access;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Service.CommunityService;
import com.example.ComUnity.Service.MemberCommunityService;
import com.example.ComUnity.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/com")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    PostService postService;

    @Autowired
    MemberCommunityService memberCommunityService;

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

        Access access = memberCommunityService.getAccess(name, member.getUsername());

        model.addAttribute("community", community);

        model.addAttribute("posts", communityService.getPosts(name));

        model.addAttribute("access", access);

        System.out.println("User: " + member + "\nAccess: " + access);

        return "show-community";
    }

    @GetMapping("/{name}/join")
    public String addToCommunity(@PathVariable String name, @AuthenticationPrincipal Member member)
    {

        memberCommunityService.addMember(name, member);

        System.out.println("redirect:/com/" + name);

        return "redirect:/com/" + name;
    }

    @GetMapping("/{name}/monitor")
    public String monitor(@PathVariable String name, @AuthenticationPrincipal Member member, Model model)
    {
        Access access = memberCommunityService.getAccess(name, member.getUsername());

        if(access.isSheriff()){
            model.addAttribute("members", communityService.getByName(name).getMembers());

            model.addAttribute("sheriff", member.getUsername());

            model.addAttribute("community", name);

            return "monitor";
        }

        return "redirect:/forbidden";
    }

    @GetMapping("/{name}/post")
    public String post(@PathVariable String name, @AuthenticationPrincipal Member member, Model model)
    {
        Access access = memberCommunityService.getAccess(name, member.getUsername());

        if(access.isPost_access()){

            model.addAttribute("community", name);

            model.addAttribute("postForm", new PostForm());

            return "post";
        }

        return "redirect:/forbidden";
    }

    @PostMapping("/{name}/post")
    public String post(@PathVariable String name, @AuthenticationPrincipal Member member, @Valid @ModelAttribute PostForm postForm,
                       Errors errors)
    {
        if(errors.hasErrors())
            return "redirect:/com/" + name +"/post";

        Community community = communityService.getByName(name);

        postService.savePost(postForm, community, member);

        return "redirect:/com/" + name;
    }

    @PostMapping("/{comName}/remove/{username}")
    public String removeUser(@PathVariable String comName, @PathVariable String username, @AuthenticationPrincipal Member member)
    {

        boolean deleted = memberCommunityService.removeMember(comName, username, member.getUsername());

        if(deleted)
            return "redirect:/com/" + comName + "/monitor";

        return "redirect:/forbidden";
    }

}
