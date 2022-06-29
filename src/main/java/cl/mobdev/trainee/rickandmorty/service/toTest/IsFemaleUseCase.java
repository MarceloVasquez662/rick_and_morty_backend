package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.exception.GenderException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.IIsFemaleInterface;
import org.springframework.stereotype.Service;


public class IsFemaleUseCase {

    private String genderRequired = "Female";
    private final IIsFemaleInterface iIsFemaleInterface;

    public IsFemaleUseCase(IIsFemaleInterface iIsFemaleInterface) {
        this.iIsFemaleInterface = iIsFemaleInterface;
    }

    public Character execute(int id) {
        String characterGender = iIsFemaleInterface.getFemaleCharacter(1).getGender();
        if (characterGender == null) {
            throw new GenderException("El character no es mujer");
        } else {
            if (genderRequired.equals(characterGender)) {
                return iIsFemaleInterface.getFemaleCharacter(id);
            } else {
                throw new GenderException("El character no es mujer");
            }
        }
    }
}
