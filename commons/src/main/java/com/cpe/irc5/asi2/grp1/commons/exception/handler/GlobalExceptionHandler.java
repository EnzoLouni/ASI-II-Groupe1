package com.cpe.irc5.asi2.grp1.commons.exception.handler;

import com.cpe.irc5.asi2.grp1.commons.exception.ErrorResponse;
import com.cpe.irc5.asi2.grp1.commons.exception.NotFoundResourceException;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.ConnectException;
import java.util.Collections;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.ACTIVEMQ_DOWN;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.DATABASE_DOWN;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.RESOURCE_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.RESOURCE_NOT_FOUND_DETAILS;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SERVICE_DOWN;

@ControllerAdvice
@Slf4j
@Component
public abstract class GlobalExceptionHandler implements ErrorHandler {
    @ExceptionHandler(value = {CannotCreateTransactionException.class, DataAccessResourceFailureException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleNoDatabaseConnexionException()
    {
        log.error(DATABASE_DOWN);
        return new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), false, DATABASE_DOWN, Collections.singletonList(DATABASE_DOWN));
    }

    @ExceptionHandler(value = ConnectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleNoActiveMQException()
    {
        log.error(ACTIVEMQ_DOWN);
        return new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), false, ACTIVEMQ_DOWN, Collections.singletonList(ACTIVEMQ_DOWN));
    }

    @ExceptionHandler(value = FeignException.FeignClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleServiceErrorsException(FeignException.FeignClientException ex)
    {
        log.error(ACTIVEMQ_DOWN);
        return new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), false, SERVICE_DOWN, Collections.singletonList(ACTIVEMQ_DOWN));
    }

    @ExceptionHandler(value = NotFoundResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleNotFoundResourceException(NotFoundResourceException ex)
    {
        String resourceNotFound = String.format(RESOURCE_NOT_FOUND_DETAILS,ex.getDataId(),ex.getRequestType());
        log.error(resourceNotFound);
        return new ErrorResponse(HttpStatus.NOT_MODIFIED.value(), false, RESOURCE_NOT_FOUND, Collections.singletonList(resourceNotFound));
    }

    public abstract ErrorResponse handleNoServiceException(RetryableException ex);
}
