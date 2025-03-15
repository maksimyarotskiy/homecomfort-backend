package com.homecomfort.homecomfort.repository;

import com.homecomfort.homecomfort.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
