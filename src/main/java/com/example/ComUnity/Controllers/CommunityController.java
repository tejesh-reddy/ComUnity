package com.example.ComUnity.Controllers;


import com.example.ComUnity.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        return "communties-all";
    }

}
