package com.hf.giftlist.application.repository.gift.client;

import com.hf.giftlist.application.repository.gift.entity.Gift;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GiftClient extends JpaRepository<Gift, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Gift g WHERE g.id = :id AND g.giftList.user.id = :userId")
    void deleteByIdAndUserIdAndGiftListId(String id, String userId);
}
