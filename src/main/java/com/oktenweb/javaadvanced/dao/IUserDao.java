package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {

  User findByUsername(String username);
}
