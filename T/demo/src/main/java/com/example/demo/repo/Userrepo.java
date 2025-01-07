package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface Userrepo extends JpaRepository<User, Long> {

  // jpaRepository gives access for crud operations for the fields which we select

  Optional<User> findByEmail(String email);

  Optional<User> findById(Long userId);

}
