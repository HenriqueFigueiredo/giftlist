package com.hf.giftlist.application.repository.login.client;

import com.hf.giftlist.application.repository.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryClient extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
