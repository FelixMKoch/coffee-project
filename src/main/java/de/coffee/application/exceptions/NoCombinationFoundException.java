package de.coffee.application.exceptions;

/**
 * Exception that is thrown if a Review is being saved, while there is no entry
 * for the Coffee-Shop Combination in the Database.
 */
public class NoCombinationFoundException extends Exception{

    public NoCombinationFoundException(){};

    public NoCombinationFoundException(String message){
        super(message);
    }

    public NoCombinationFoundException(String message, Throwable inner){
        super(message, inner);
    }
}
