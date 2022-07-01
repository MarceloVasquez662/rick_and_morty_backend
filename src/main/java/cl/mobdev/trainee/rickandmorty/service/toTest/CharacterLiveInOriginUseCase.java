package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.exception.LocationException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.stereotype.Service;


public class CharacterLiveInOriginUseCase {

    private String unknown = "unknown";


    public boolean execute(Character character) {

        if (character.getLocation()==null && character.getOrigin()==null || unknown.equals(character.getLocation().getName()) && unknown.equals(character.getOrigin().getName())) {
            throw new LocationException("Origin and location unknown");
        } else {
            if (character.getLocation().getName()==null || unknown.equals(character.getOrigin().getName())) {
                throw new LocationException("Origin unknown");
            } else {
                if (character.getLocation()==null || unknown.equals(character.getLocation().getName())) {
                    throw new LocationException("Location unknown");
                } else {
                    if (character.getOrigin().getName().equals(character.getLocation().getName())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

    }
}
