package com.example.ComUnity.Dao;

import com.example.ComUnity.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Integer> {

}
