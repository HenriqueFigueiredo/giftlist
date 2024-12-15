package com.hf.giftlist.application.exception;

import lombok.Getter;

@Getter
public enum MessageEnum {
    GENERIC("1000", "Sorry, try again in a moment."),
    NEW_USER("1001", "You are a new user."),
    INVALID_CODE("1002", "Invalid code."),
    INVALID_PAYLOAD("1004", "Payload invalid."),
    SESSION_EXPIRED("1005", "Session expired."),
    GIFT_LIST_MAX_QUANTITY("1006", "Maximum number of lists reached."),
    GIFT_LIST_MAX_SIZE("1007", "Maximum number of gifts reached."),
    SEND_MAIL_ERROR("1008", "Error sending email.")
    ;

    private final String code;
    private final String msg;

    MessageEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
