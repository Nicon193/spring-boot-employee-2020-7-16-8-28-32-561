package com.thoughtworks.springbootemployee.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    public static final String NO_SUCH_DATA = "There is no such data";
    public static final String ERROR_OPERATION = "Error operation";

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noSuchDataException() {
        return NO_SUCH_DATA;
    }

    @ExceptionHandler(ErrorOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String errorOperationException() {
        return ERROR_OPERATION;
    }

}
