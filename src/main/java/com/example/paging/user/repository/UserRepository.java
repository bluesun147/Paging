package com.example.paging.user.repository;

import com.example.paging.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByAge(Long age, Pageable pageable);
}
