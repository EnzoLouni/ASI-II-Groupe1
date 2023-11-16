package com.cpe.irc5.asi2.grp1.user_manager.config;

import com.cpe.irc5.asi2.grp1.commons.exception.ErrorResponse;
import com.cpe.irc5.asi2.grp1.commons.exception.handler.GlobalExceptionHandler;
import com.fasterxml.jackson.core.JsonParseException;
import feign.RetryableException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.BAD_FORMAT;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.USER_FORMAT;

@ControllerAdvice("com.cpe.irc5.asi2.grp1.user_manager")
@Slf4j
@NoArgsConstructor
public class UserExceptionHandler extends GlobalExceptionHandler {
    @ExceptionHandler(value = JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleDataFormatException() {
        log.error(USER_FORMAT);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), false, BAD_FORMAT, Collections.singletonList(USER_FORMAT));
    }

    @Override
    public ErrorResponse handleNoServiceException(RetryableException ex) {
        return null;
    }

    @Override
    public void handleError(Throwable t) {

    }
}
