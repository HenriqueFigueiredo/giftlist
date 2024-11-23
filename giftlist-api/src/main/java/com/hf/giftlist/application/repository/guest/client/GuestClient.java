package com.hf.giftlist.application.repository.guest.client;

import com.hf.giftlist.application.repository.gift.entity.Gift;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GuestClient extends JpaRepository<Gift, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Gift g SET g.guest.id = :guestId WHERE g.id = :giftId AND g.giftList.id = :giftListId")
    int updateGuestByGiftIdAndGiftListId(String guestId, String giftId, String giftListId);
}
