package com.cpe.irc5.asi2.grp1.logger_manager.config;

import com.cpe.irc5.asi2.grp1.commons.exception.ErrorResponse;
import com.cpe.irc5.asi2.grp1.commons.exception.handler.GlobalExceptionHandler;
import feign.RetryableException;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice("com.cpe.irc5.asi2.grp1.card_manager")
@NoArgsConstructor
public class LoggerExceptionHandler extends GlobalExceptionHandler {
    @Override
    public ErrorResponse handleNoServiceException(RetryableException ex) {
        return null;
    }

    @Override
    public void handleError(Throwable t) {

    }
}
