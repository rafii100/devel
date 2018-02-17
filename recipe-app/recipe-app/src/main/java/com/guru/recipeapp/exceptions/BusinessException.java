package com.guru.recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BusinessException extends RuntimeException{
	
    public BusinessException() {
        super();
    }

   
    public BusinessException(String message) {
        super(message);
    }

}
