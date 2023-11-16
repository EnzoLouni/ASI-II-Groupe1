package com.cpe.irc5.asi2.grp1.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private boolean status;
    private String message;
    private List<String> errors;
}
