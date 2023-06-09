package com.ecommerce.user.repository;

import java.util.Optional;

import com.ecommerce.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  User findByEmail(String email);

  public User findByResetPasswordToken(String token);
}
