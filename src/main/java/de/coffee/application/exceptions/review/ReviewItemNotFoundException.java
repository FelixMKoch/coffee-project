package de.coffee.application.exceptions.review;

public class ReviewItemNotFoundException extends Exception {

    public ReviewItemNotFoundException(){}

    public ReviewItemNotFoundException(String message) {
        super(message);
    }

    public ReviewItemNotFoundException(String message, Throwable inner) {
        super(message, inner);
    }
}
