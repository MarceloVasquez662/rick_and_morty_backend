package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.stereotype.Service;


public class CharacterIsFromEarthUseCase {
    public boolean execute(Character character) {

        if (null == character.getOrigin() || null == character.getOrigin().getName()) {
            return false;
        } else {

            if (character.getOrigin().getName().equals("Earth")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
