package com.hf.giftlist.domain.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoggedUser {
    private String id;
    private String name;
    private String lastname;
    private String email;
}
