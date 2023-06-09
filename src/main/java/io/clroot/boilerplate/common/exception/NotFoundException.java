package io.clroot.boilerplate.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException{

    public NotFoundException(final String message){
        super(HttpStatus.NOT_FOUND,message);
    }

}
