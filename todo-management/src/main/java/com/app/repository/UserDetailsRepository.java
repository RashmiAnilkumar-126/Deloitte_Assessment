package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUserName(String userName);
}
