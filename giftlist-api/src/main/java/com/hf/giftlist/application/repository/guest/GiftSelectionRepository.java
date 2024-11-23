package com.hf.giftlist.application.repository.guest;

import com.hf.giftlist.application.repository.guest.client.GuestClient;
import com.hf.giftlist.domain.model.guest.GiftSelection;
import com.hf.giftlist.domain.repository.guest.IGiftSelectionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftSelectionRepository implements IGiftSelectionRepository {

    private final GuestClient client;

    public GiftSelectionRepository(final GuestClient client) {
        this.client = client;
    }

    @Override
    public void select(final GiftSelection gift) {
        var result = this.client.updateGuestByGiftIdAndGiftListId(gift.getUserId(), gift.getGiftId(), gift.getGiftListId());
        System.out.printf("Um total de %s foram atualizados", Integer.toString(result));
    }
}
