package com.hf.giftlist.domain.exception;

public class GiftListMaxQuantityException extends RuntimeException {
    public GiftListMaxQuantityException() {
        super("Maximum number of lists reached.");
    }
}
