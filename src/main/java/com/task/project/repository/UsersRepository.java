package com.task.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.project.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
