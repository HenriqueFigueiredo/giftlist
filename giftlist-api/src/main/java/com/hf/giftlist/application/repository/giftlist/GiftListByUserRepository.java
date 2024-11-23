package com.hf.giftlist.application.repository.giftlist;

import com.hf.giftlist.application.repository.giftlist.client.GiftListClient;
import com.hf.giftlist.domain.model.giftlist.SimpleGiftList;
import com.hf.giftlist.domain.repository.giftlist.IGiftListByUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiftListByUserRepository implements IGiftListByUserRepository {

    private final GiftListClient client;

    public GiftListByUserRepository(final GiftListClient client) {
        this.client = client;
    }

    @Override
    public List<SimpleGiftList> findAll(final String userId) {
        var result = this.client.findByUserId(userId);
        return result.stream().map(x -> new SimpleGiftList(x.getId(), x.getName(), x.getDescription())).toList();
    }
}
