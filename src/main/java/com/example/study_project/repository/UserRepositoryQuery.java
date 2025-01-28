package com.example.study_project.repository;

import com.example.study_project.entity.User;

import java.util.List;

public interface UserRepositoryQuery {

    List<User> findByUsernameContain(String username);
    List<User> findByUsernameStartWith(String username);

    List<User> findByEmailContain(String email);

    List<User> findByFixEmailContain();

    List<User> checkRole();

    List<User> userHasOrder();

    List<User> orderCountSort();
}
