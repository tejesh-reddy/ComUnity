package com.example.ComUnity;

import com.example.ComUnity.Dao.CommunityDao;
import com.example.ComUnity.Dao.MemberDao;
import com.example.ComUnity.Dao.PostDao;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class Setup {

    @Autowired
    MemberDao memberDao;

    @Autowired
    CommunityDao communityDao;

    @Autowired
    PostDao postDao;


    public void saveMembers(PasswordEncoder passwordEncoder)
    {
        ArrayList<Member> members = new ArrayList<>();


        members.add(new Member("john_doe", passwordEncoder.encode("password"), "johnny@ghoo.com"));
        members.add(new Member("queen_elizabeth" ,passwordEncoder.encode("password"), "Elly@ghoo.com"));
        members.add(new Member("marshall_cook", passwordEncoder.encode("password"), "Marsh@ghoo.com"));

        ArrayList<Community> communities = new ArrayList<>();

        communities.add(new Community("News", "A community dedicated to journalism", Community.Type.PUBLIC, members.get(1)));
        communities.add(new Community("Funny", "A good laugh is worth joking for", Community.Type.PUBLIC, members.get(0)));


        ArrayList<Post> posts = new ArrayList<>();

        posts.add(new Post("Good ol' joke", "A horse goes to a bar. 'Why the long face?' says...", members.get(0), communities.get(1)));
        posts.add(new Post("The WorldPost", "There's a new sheriff in town", members.get(1), communities.get(0)));
        posts.add(new Post("Science", "Hydrogen has one atom", members.get(0), communities.get(0)));

        members.get(0).addPost(posts.get(0));
        members.get(0).addPost(posts.get(2));
        members.get(1).addPost(posts.get(1));

        communities.get(0).addPost(posts.get(0));
        communities.get(1).addPost(posts.get(1));
        communities.get(1).addPost(posts.get(2));

        communities.get(1).addMember(members.get(2));

        for(Member member : members)
        {
            memberDao.save(member);
        }

        for(Community community : communities){
            communityDao.save(community);
        }

        for(Post post : posts)
            postDao.save(post);

    }
}
