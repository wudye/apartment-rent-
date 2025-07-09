package com.mwu.webadmin.repository;

import com.mwu.model.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemLoginRepository extends JpaRepository<SystemUser, Long> {

    Optional<SystemUser> findByUsername(String username);


    // Define custom query methods if needed
    // For example, find by username or email
    // SystemUser findByUsername(String username);
    // SystemUser findByEmail(String email);
}
