package com.exercise.user.repository;

import com.exercise.user.domain.User;

import java.util.List;


public interface IuserRepository {
    long count();

    User save(User user);

    User findByUsername(String username);

    User deleteByUsername(String username);

    User updateByUsername(User user);

    List<User> findAll();


}
