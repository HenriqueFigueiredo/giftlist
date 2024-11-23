package com.hf.giftlist.application.repository.gift;

import com.hf.giftlist.application.repository.gift.client.GiftClient;
import com.hf.giftlist.application.repository.gift.entity.Gift;
import com.hf.giftlist.application.repository.giftlist.client.GiftListClient;
import com.hf.giftlist.domain.model.gift.NewGift;
import com.hf.giftlist.domain.repository.gift.IGiftCreationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftCreationRepository implements IGiftCreationRepository {

    private final GiftListClient giftListClient;
    private final GiftClient giftClient;

    public GiftCreationRepository(final GiftListClient giftListClient, final GiftClient giftClient) {
        this.giftListClient = giftListClient;
        this.giftClient = giftClient;
    }

    @Override
    public void create(final NewGift gift) {
        var giftList = this.giftListClient.findByIdAndUserId(gift.getListId(), gift.getUserId()).orElseThrow(() -> new RuntimeException("Invalid list"));
        var entity = new Gift(giftList, gift.getName(), gift.getUrl());
        giftClient.save(entity);
    }
}
