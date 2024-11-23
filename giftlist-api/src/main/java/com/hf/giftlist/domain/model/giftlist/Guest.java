package com.hf.giftlist.domain.model.giftlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Guest {
    private String name;
    private String lastName;
    private String email;
}
