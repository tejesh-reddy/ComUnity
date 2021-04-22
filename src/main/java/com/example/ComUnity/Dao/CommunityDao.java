package com.example.ComUnity.Dao;


import com.example.ComUnity.Domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityDao extends JpaRepository<Community, String> {
}
