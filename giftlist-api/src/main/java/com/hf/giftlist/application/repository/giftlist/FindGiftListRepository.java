package com.hf.giftlist.application.repository.giftlist;

import com.hf.giftlist.application.repository.giftlist.client.GiftListClient;
import com.hf.giftlist.application.repository.login.entity.User;
import com.hf.giftlist.domain.model.giftlist.Gift;
import com.hf.giftlist.domain.model.giftlist.GiftList;
import com.hf.giftlist.domain.model.giftlist.Guest;
import com.hf.giftlist.domain.repository.giftlist.IFindGiftListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FindGiftListRepository implements IFindGiftListRepository {

    private final GiftListClient client;

    public FindGiftListRepository(final GiftListClient client) {
        this.client = client;
    }

    @Override
    public Optional<GiftList> find(final String listId) {
        var result = this.client.findById(listId);
        return result.map(x -> new GiftList(x.getId(), x.getName(), x.getDescription(), this.map(x.getGifts())));
    }

    private List<Gift> map(final List<com.hf.giftlist.application.repository.gift.entity.Gift> gifts) {
        return gifts.stream().map(x -> new Gift(x.getId(), x.getName(), x.getUrl(), this.map(x.getGuest()))).toList();
    }

    private Guest map(final User user) {
        return user != null ? new Guest(user.getName(), user.getLastName(), user.getEmail()) : null;
    }
}
