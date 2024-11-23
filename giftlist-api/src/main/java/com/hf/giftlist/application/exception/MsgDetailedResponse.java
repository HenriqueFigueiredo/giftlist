package com.hf.giftlist.application.exception;

import java.util.Map;

public record MsgDetailedResponse(String cod, String msg, Map<String, String> details) {
}
