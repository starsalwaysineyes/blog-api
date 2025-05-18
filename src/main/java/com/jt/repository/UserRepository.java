package com.jt.repository;

import com.jt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByAccount(String account);




}
