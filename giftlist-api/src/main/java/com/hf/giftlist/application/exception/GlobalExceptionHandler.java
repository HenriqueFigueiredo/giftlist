package com.hf.giftlist.application.exception;

import com.hf.giftlist.domain.exception.GiftListMaxQuantityException;
import com.hf.giftlist.domain.exception.GiftListMaxSizeException;
import com.hf.giftlist.domain.exception.SendEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.hf.giftlist.application.exception.MessageEnum.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MsgDetailedResponse> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });
        var msg = new MsgDetailedResponse(INVALID_PAYLOAD.getCode(), INVALID_PAYLOAD.getMsg(), details);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSessionException.class)
    public ResponseEntity<MsgResponse> handleSessionExceptions(final InvalidSessionException ex) {
        logger.error("Invalid session exception.", ex);
        var msg = new MsgResponse(SESSION_EXPIRED.getCode(), SESSION_EXPIRED.getMsg());
        return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GiftListMaxQuantityException.class)
    public ResponseEntity<MsgResponse> handleGiftListMaxQuantityException(final GiftListMaxQuantityException ex) {
        var msg = new MsgResponse(GIFT_LIST_MAX_QUANTITY.getCode(), GIFT_LIST_MAX_QUANTITY.getMsg());
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GiftListMaxSizeException.class)
    public ResponseEntity<MsgResponse> handleGiftListMaxSizeException(final GiftListMaxSizeException ex) {
        var msg = new MsgResponse(GIFT_LIST_MAX_SIZE.getCode(), GIFT_LIST_MAX_SIZE.getMsg());
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<MsgResponse> handleSendEmailException(final SendEmailException ex) {
        var msg = new MsgResponse(GIFT_LIST_MAX_SIZE.getCode(), GIFT_LIST_MAX_SIZE.getMsg());
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<MsgResponse> handleSessionExceptions(final Throwable ex) {
        logger.error("Generic error.", ex);
        var msg = new MsgResponse(GENERIC.getCode(), GENERIC.getMsg());
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
