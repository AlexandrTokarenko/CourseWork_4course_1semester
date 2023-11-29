package com.example.coursework.repository;

import com.example.coursework.entity.LockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockUserRepository extends JpaRepository<LockUser, Integer> {
}