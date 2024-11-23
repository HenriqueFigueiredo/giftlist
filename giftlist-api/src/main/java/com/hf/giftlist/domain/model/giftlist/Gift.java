package com.hf.giftlist.domain.model.giftlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Gift {
    private String id;
    private String name;
    private String url;
    private Guest guest;
}
