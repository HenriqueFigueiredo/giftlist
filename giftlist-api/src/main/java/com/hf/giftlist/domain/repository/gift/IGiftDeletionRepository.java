package com.hf.giftlist.domain.repository.gift;

public interface IGiftDeletionRepository {
    void delete(String listId, String giftId, String userId);
}
