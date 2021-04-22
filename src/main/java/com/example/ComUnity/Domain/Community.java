package com.example.ComUnity.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Community {

    @Id
    @Column(name = "name")
    private String name;

    private String description;
    private Type type;

    public enum Type {PUBLIC, INVITE_ONLY};

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Post> posts = new HashSet<>();

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "community_member",
    joinColumns = {
            @JoinColumn(name = "username", referencedColumnName = "username",
    nullable = false, updatable = false)
    },
    inverseJoinColumns = {
            @JoinColumn(name = "community", referencedColumnName = "name",
            nullable = false, updatable = false)
    })
    private Set<Member> members = new HashSet<>();*/


    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "community_moderator",
    joinColumns = {
            @JoinColumn(name = "username", referencedColumnName = "username",
    nullable = false, updatable = false)
    },
    inverseJoinColumns = {
            @JoinColumn(name = "community", referencedColumnName = "name",
            nullable = false, updatable = false)
    })
    private Set<Member> moderators = new HashSet<>();*/

    public Community(String name, String description, Type type)
    {
        this.name = name;
        this.description = description;
        this.type = type;
    }


    public void addPost(Post post)
    {
        posts.add(post);
    }

    public String toString()
    {
        return "Community: " + name + " : " + description + "[ " + type + " ];";
    }
}
