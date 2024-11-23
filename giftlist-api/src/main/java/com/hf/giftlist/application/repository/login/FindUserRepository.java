package com.hf.giftlist.application.repository.login;

import com.hf.giftlist.application.repository.login.client.UserRepositoryClient;
import com.hf.giftlist.domain.model.login.LoggedUser;
import com.hf.giftlist.domain.repository.login.IFindUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FindUserRepository implements IFindUserRepository {

    private final UserRepositoryClient client;

    public FindUserRepository(final UserRepositoryClient client) {
        this.client = client;
    }

    @Override
    public Optional<LoggedUser> findByEmail(String email) {
        var entity = this.client.findByEmail(email);
        return entity.map(user -> new LoggedUser(user.getId(), user.getName(), user.getLastName(), user.getEmail()));
    }
}
