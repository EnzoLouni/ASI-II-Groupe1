package com.cpe.irc5.asi2.grp1.store_manager.config;

import com.cpe.irc5.asi2.grp1.commons.errors.ErrorResponse;
import com.cpe.irc5.asi2.grp1.commons.errors.handler.GlobalExceptionHandler;
import feign.RetryableException;

public class StoreExceptionHandler extends GlobalExceptionHandler {
    @Override
    public ErrorResponse handleNoServiceException(RetryableException ex) {
        return null;
    }
}
