package com.securebank.api.repository;

import com.securebank.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.email = :email")
    Optional<User> findByEmailWithAccounts(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' AND u.isVerified = true")
    java.util.List<User> findActiveVerifiedUsers();
}
