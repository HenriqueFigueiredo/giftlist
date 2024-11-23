package com.hf.giftlist.application.repository.giftlist;

import com.hf.giftlist.application.repository.giftlist.client.GiftListClient;
import com.hf.giftlist.application.repository.giftlist.entity.GiftList;
import com.hf.giftlist.application.repository.login.client.UserRepositoryClient;
import com.hf.giftlist.domain.model.giftlist.NewGiftList;
import com.hf.giftlist.domain.repository.giftlist.IGiftListCreationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftListCreationRepository implements IGiftListCreationRepository {

    private final GiftListClient client;
    private final UserRepositoryClient userClient;

    public GiftListCreationRepository(final GiftListClient client, final UserRepositoryClient userClient) {
        this.client = client;
        this.userClient = userClient;
    }

    @Override
    public String create(NewGiftList giftList) {
        var user = this.userClient.findById(giftList.getUserId()).orElseThrow(
                () -> new RuntimeException("User not found with id " + giftList.getUserId()));
        var giftListEntity = new GiftList(user, giftList.getName(), giftList.getDescription());
        var result = this.client.save(giftListEntity);
        return result.getId();
    }
}
