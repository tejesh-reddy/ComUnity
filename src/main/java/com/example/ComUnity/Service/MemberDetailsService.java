package com.example.ComUnity.Service;

import com.example.ComUnity.Dao.MemberDao;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Member> memberOptional = memberDao.findById(s);

        return memberOptional.orElse(null);
    }

    public List<Member> getAll()
    {
        return memberDao.findAll();
    }

    public Member getByUsername(String name)
    {
        return memberDao.findById(name).orElse(null);
    }

    @Transactional
    public List<Post> getPosts(String username)
    {
        Optional<Member> member = memberDao.findById(username);

        if(member.isPresent())
            return member.get().getPosts();

        return null;
    }
}
