package cl.mobdev.trainee.rickandmorty.exception;

public class CharacterNotFoundException extends RuntimeException{
    public CharacterNotFoundException(String message) {
        super(message);
    }
}
