package com.cpe.irc5.asi2.grp1.commons.errors.handler;

import com.cpe.irc5.asi2.grp1.commons.errors.ErrorResponse;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.cpe.irc5.asi2.grp1.auth_manager.service")
@Slf4j
@Component
public class AuthenticationExceptionHandler extends GlobalExceptionHandler {
    @Override
    @ExceptionHandler(value = RetryableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoServiceException(RetryableException ex) {
        /*log.error(ex.getMessage());
        return new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), false, SERVICE_DOWN, Collections.singletonList(SPECIFIED_SERVICE_DOWN));*/
        return null;
    }
}
