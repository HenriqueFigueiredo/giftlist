package com.hf.giftlist.application.repository.login;

import com.hf.giftlist.application.repository.login.client.UserRepositoryClient;
import com.hf.giftlist.application.repository.login.entity.User;
import com.hf.giftlist.domain.model.login.LoggedUser;
import com.hf.giftlist.domain.model.login.NewUser;
import com.hf.giftlist.domain.repository.login.IUserCreationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserCreationRepository implements IUserCreationRepository {

    private final UserRepositoryClient client;

    public UserCreationRepository(final UserRepositoryClient client) {
        this.client = client;
    }

    @Override
    public LoggedUser create(final NewUser newUser) {
        var entity = new User(newUser.getName(), newUser.getLastName(), newUser.getEmail());
        var user = this.client.save(entity);
        return new LoggedUser(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }
}
