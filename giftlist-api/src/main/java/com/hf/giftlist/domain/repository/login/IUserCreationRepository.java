package com.hf.giftlist.domain.repository.login;

import com.hf.giftlist.domain.model.login.LoggedUser;
import com.hf.giftlist.domain.model.login.NewUser;

public interface IUserCreationRepository {

    LoggedUser create(NewUser newUser);

}
