package com.hf.giftlist.domain.repository.giftlist;

import com.hf.giftlist.domain.model.giftlist.GiftList;

import java.util.Optional;

public interface IFindGiftListRepository {
    Optional<GiftList> find(String listId);
}
