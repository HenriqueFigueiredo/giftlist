package com.hf.giftlist.application.repository.giftlist.client;

import com.hf.giftlist.application.repository.giftlist.entity.GiftList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GiftListClient extends JpaRepository<GiftList, String> {

    List<GiftList> findByUserId(String userId);

    @Query("SELECT gl FROM GiftList gl WHERE gl.id = :giftListId AND gl.user.id = :userId")
    Optional<GiftList> findByIdAndUserId(String giftListId, String userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM GiftList g WHERE g.id = :giftListId AND g.user.id = :userId")
    void deleteByUserIdAndGiftListId(String userId, String giftListId);
}
