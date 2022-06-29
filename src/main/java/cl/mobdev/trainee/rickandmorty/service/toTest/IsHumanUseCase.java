package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.IIsHumanInterface;
import org.springframework.stereotype.Service;


public class IsHumanUseCase {

    private final IIsHumanInterface iIsHumanInterface;
    private String humanSpecie = "Human";

    public IsHumanUseCase(IIsHumanInterface iIsHumanInterface) {
        this.iIsHumanInterface = iIsHumanInterface;
    }

    public boolean execute(int id) {
        Character character = iIsHumanInterface.getCharacterByID(id);
        if (humanSpecie.equals(character.getSpecies())) {
            return true;
        } else {
            return false;
        }
    }
}
