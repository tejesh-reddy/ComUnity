package com.example.ComUnity.Service;

import com.example.ComUnity.DTO.PostForm;
import com.example.ComUnity.Dao.PostDao;
import com.example.ComUnity.Domain.Community;
import com.example.ComUnity.Domain.Member;
import com.example.ComUnity.Domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    PostDao postDao;

    @Transactional
    public void savePost(PostForm postForm, Community community, Member author)
    {
        Post post = new Post(author, community);

        post.setContent(postForm.getContent());
        post.setTitle(postForm.getTitle());

        postDao.save(post);
    }

}
