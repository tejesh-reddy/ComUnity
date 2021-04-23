package com.example.ComUnity.rest;


import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mem")
public class MemberApi {

    @Autowired
    private MemberDetailsService memberDetailsService;

    @GetMapping("/all")
    public List<Member> getAllMembers()
    {
        List<Member> members = new ArrayList<>();
        for(Member member : memberDetailsService.getAll())
            passwordStar(member);
        return members;
    }

    @GetMapping("/{username}")
    public Member getMemberByUsername(@PathVariable String username)
    {
         Member member = memberDetailsService.getByUsername(username);
         return passwordStar(member);
    }

    @PostMapping(value = "/new", consumes = "application/json")
    @ResponseBody
    public Member addMember(@RequestBody Member member)
    {

        memberDetailsService.save(member);

        return member;
    }

    private Member passwordStar(Member member)
    {
        Member member1 = new Member(member.getUsername());

        member1.setPassword("*********");
        member1.setEmail(member.getEmail());
        member1.setPosts(member.getPosts());
        member1.setJoiningDate(member.getJoiningDate());

        return member1;
    }

}
