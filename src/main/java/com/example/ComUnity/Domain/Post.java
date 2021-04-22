package com.example.ComUnity.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int post_id;

    private String title;
    private String content;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private final Member author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "community_name", nullable = false)
    private final Community community;

    public Post(Member member, Community community)
    {
        this.author = member;
        this.community = community;

    }


    public Post(String title, String content, Member member, Community community)
    {
        this(member, community);
        this.title = title;
        this.content = content;
    }

    public String toString()
    {
        return "Post: " + title;
    }

}
