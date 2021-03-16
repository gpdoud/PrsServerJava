package com.maxtrain.prs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.maxtrain.prs.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsernameAndPassword(String uname, String pwd);
}