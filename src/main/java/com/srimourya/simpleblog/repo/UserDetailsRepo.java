package com.srimourya.simpleblog.repo;

import com.srimourya.simpleblog.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDetailsRepo extends JpaRepository<UsersModel, UUID> {
    UsersModel findByUsername(String username);
}
