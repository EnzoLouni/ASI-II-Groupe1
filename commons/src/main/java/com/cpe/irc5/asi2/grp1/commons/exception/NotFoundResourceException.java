package com.cpe.irc5.asi2.grp1.commons.exception;

import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundResourceException extends RuntimeException {
    private RequestType requestType;
    private Integer dataId;
}
