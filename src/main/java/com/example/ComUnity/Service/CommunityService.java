package com.example.ComUnity.Service;

import com.example.ComUnity.Dao.CommunityDao;
import com.example.ComUnity.Domain.Access;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommunityService {

    @Autowired
    CommunityDao communityDao;

    @Autowired
    MemberDetailsService memberService;


    public List<Community> getAll()
    {
        return communityDao.findAll();
    }

    public Community getByName(String name)
    {
        return communityDao.findById(name).orElse(null);
    }

    @Transactional
    public Set<Post> getPosts(String name)
    {
        Optional<Community> community = communityDao.findById(name);

        if(community.isPresent())
            return community.get().getPosts();

        return null;
    }

    @Transactional
    public Access getAccess(String communityName, String username)
    {
        Community community = getByName(communityName);
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

        if(access.isJoin_access()) {
            Community community = getByName(communityName);
            community.addMember(member);
            memberService.addCommunity(username, community);
        }
    }

}
