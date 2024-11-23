package com.hf.giftlist.domain.model.giftlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SimpleGiftList {
    private String id;
    private String name;
    private String description;
}
