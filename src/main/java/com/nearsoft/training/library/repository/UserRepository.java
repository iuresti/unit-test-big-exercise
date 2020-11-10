package com.nearsoft.training.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nearsoft.training.library.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
