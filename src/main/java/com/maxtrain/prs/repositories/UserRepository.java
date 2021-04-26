package com.maxtrain.prs.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.maxtrain.prs.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(String uname, String pwd);
}