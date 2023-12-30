package com.hrms.secure.repository;

import org.springframework.data.repository.CrudRepository;

import com.hrms.secure.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
