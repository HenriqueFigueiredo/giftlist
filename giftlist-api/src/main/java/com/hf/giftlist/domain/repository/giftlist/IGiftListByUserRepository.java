package com.hf.giftlist.domain.repository.giftlist;

import com.hf.giftlist.domain.model.giftlist.SimpleGiftList;

import java.util.List;

public interface IGiftListByUserRepository {
    List<SimpleGiftList> findAll(String userId);
}
