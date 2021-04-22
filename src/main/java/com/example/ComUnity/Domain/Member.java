package com.example.ComUnity.Domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Member implements UserDetails {

    @Id
    @Column(name = "username")
    private final String username;

    @EqualsAndHashCode.Exclude
    private String password;
    @EqualsAndHashCode.Exclude
    private String email;
    @EqualsAndHashCode.Exclude
    private Date joiningDate;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    Set<Community> communities = new HashSet<>();

    @OneToOne(mappedBy = "sheriff", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    Community sheriffOf;

    public Member(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        joiningDate = new Date();
    }

    public void addPost(Post post)
    {
        posts.add(post);
    }

    public void addMembership(Community community)
    {
        communities.add(community);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toString()
    {
        return "Member: " + username + " @ " + email;
    }

    public void removeCommunity(Community community) {
        communities.remove(community);
    }
}
