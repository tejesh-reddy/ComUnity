package com.example.ComUnity.Service;

import com.example.ComUnity.DTO.MemberRegistration;
import com.example.ComUnity.Dao.MemberDao;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    public boolean hasUsername(String username) {

        for(Member member : getAll())
            if(member.getUsername().equals(username))
                return true;

        return false;

    }

    public void register(MemberRegistration memberRegistration, PasswordEncoder passwordEncoder) {

        String username = memberRegistration.getUsername();
        String password = passwordEncoder.encode(memberRegistration.getPassword());
        String email = memberRegistration.getEmail();

        Member member = new Member(username, password, email);

        memberDao.save(member);
    }

    public void save(Member member)
    {
        memberDao.save(member);
    }
}
