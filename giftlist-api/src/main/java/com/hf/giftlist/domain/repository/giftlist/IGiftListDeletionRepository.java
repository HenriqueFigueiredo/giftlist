package com.hf.giftlist.domain.repository.giftlist;

public interface IGiftListDeletionRepository {
    void delete(String userEmail, String listId);
}
