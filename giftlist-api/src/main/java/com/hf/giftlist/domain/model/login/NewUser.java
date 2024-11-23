package com.hf.giftlist.domain.model.login;

import lombok.Getter;

@Getter
public class NewUser {
    private String name;
    private String lastName;
    private String email;

    public NewUser(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
}
