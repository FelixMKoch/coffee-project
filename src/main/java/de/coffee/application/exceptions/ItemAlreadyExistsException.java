package de.coffee.application.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAlreadyExistsException extends Exception {

    public ItemAlreadyExistsException(){};

    public ItemAlreadyExistsException(String message) {
        super(message);
    }


    public ItemAlreadyExistsException(Throwable inner, String message) {
        super(message, inner);
    }

}
