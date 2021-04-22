package com.example.ComUnity.Dao;

import com.example.ComUnity.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {
}
