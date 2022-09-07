package se.lexicon.spring_bootjpalecture.exceptions;

public class EntityNotFoundException extends RuntimeException{


    public EntityNotFoundException(String message) {
        super(message);
    }
}
