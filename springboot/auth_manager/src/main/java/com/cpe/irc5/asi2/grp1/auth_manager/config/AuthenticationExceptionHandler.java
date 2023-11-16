package com.cpe.irc5.asi2.grp1.auth_manager.config;

import com.cpe.irc5.asi2.grp1.commons.exception.ErrorResponse;
import com.cpe.irc5.asi2.grp1.commons.exception.handler.GlobalExceptionHandler;
import feign.RetryableException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.cpe.irc5.asi2.grp1.auth_manager.service")
@Slf4j
@NoArgsConstructor
public class AuthenticationExceptionHandler extends GlobalExceptionHandler {
    @Override
    @ExceptionHandler(value = RetryableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoServiceException(RetryableException ex) {
        return null;
    }

    @Override
    public void handleError(Throwable t) {

    }
}
