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
import java.util.Objects;
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
    public void save(Community community){
        communityDao.save(community);
    }


}
