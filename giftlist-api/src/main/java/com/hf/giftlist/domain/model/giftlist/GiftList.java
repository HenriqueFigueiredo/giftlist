package com.hf.giftlist.domain.model.giftlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GiftList {
    private String id;
    private String name;
    private String description;
    private List<Gift> gifts;
}
