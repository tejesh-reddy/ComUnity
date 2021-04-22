package com.example.ComUnity.Service;

import com.example.ComUnity.Domain.Access;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberCommunityService {

    @Autowired
    MemberDetailsService memberService;

    @Autowired
    CommunityService communityService;

    @Transactional
    public void addCommunity(String username, Community community)
    {
        Member member = memberService.getByUsername(username);

        member.addMembership(community);
    }
    @Transactional
    public Access getAccess(String communityName, String username)
    {
        Community community = communityService.getByName(communityName);
        Access access = new Access();


        if(community.getSheriff().getUsername().equals(username)){
            access.setSheriff(true);
            access.setJoin_access(false);
            access.setPost_access(true);
            return access;
        }
        for(Member member : community.getMembers()) {
            if (member.getUsername().equals(username)) {
                access.setPost_access(true);
                access.setJoin_access(false);
                return access;
            }
        }


        // Default
        access.setJoin_access(true);
        access.setPost_access(false);
        access.setSheriff(false);

        return access;
    }

    public void addMember(String communityName, Member member)
    {
        String username = member.getUsername();
        Access access = getAccess(communityName, username);
        Community community = communityService.getByName(communityName);

        if(access.isJoin_access()) {
            community.addMember(member);
            addCommunity(username, community);
        }

        save(community, member);
    }

    public boolean removeMember(String comName, String username, String requester) {

        Member member = memberService.getByUsername(username);

        Community community = communityService.getByName(comName);

        boolean success = false;

        if(hasUser(community, username)) {
            if(isSherif(community, requester)) {
                removeMemberFromCommunity(community, member);
                success = true;
            }
        }

        save(community, member);

        return success;
    }

    private void save(Community community, Member member)
    {
        memberService.save(member);
        communityService.save(community);
    }


    private void removeMemberFromCommunity(Community community, Member member) {
        community.removeMember(member);
        member.removeCommunity(community);
    }

    private void removeCommunityFromMember(Member member, Community community)
    {
        removeMemberFromCommunity(community, member);
    }


    private boolean isSherif(Community community, String username) {

        return community.getSheriff().getUsername().equals(username);
    }

    private boolean hasUser(Community community, String username) {

        for(Member member : community.getMembers()){
            if(member.getUsername().equals(username))
                return true;
        }

        return false;
    }
}
