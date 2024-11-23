package com.hf.giftlist.domain.repository.login;

import com.hf.giftlist.domain.model.login.LoggedUser;

import java.util.Optional;

public interface IFindUserRepository {
    Optional<LoggedUser> findByEmail(String email);
}
