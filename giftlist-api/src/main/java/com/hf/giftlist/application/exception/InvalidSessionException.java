package com.hf.giftlist.application.exception;

public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException(String message) {
        super(message);
    }

  public InvalidSessionException(Throwable cause) {
    super(cause);
  }
}
