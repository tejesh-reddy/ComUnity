package com.example.ComUnity.Controllers;


import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getCommunity(@PathVariable String name, Model model)
    {
        Community community = communityService.getByName(name);

        model.addAttribute("community", community);

        model.addAttribute("posts", communityService.getPosts(name));

        return "show-community";
    }

}
