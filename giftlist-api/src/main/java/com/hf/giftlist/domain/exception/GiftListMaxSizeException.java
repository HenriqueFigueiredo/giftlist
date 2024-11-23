package com.hf.giftlist.domain.exception;

public class GiftListMaxSizeException extends RuntimeException {
    public GiftListMaxSizeException() {
        super("Maximum number of gifts reached.");
    }
}
