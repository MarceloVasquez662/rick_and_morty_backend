package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.ICharacterListInterface;
import cl.mobdev.trainee.rickandmorty.service.toTest.components.MartianTranslate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CharacterListUseCase implements ICharacterListInterface {

    private String MARS = "Mars";
    private final ICharacterListInterface characterListInterface;
    private final MartianTranslate martianTranslate;

    public CharacterListUseCase(ICharacterListInterface characterListInterface, MartianTranslate martianTranslate) {
        this.characterListInterface = characterListInterface;
        this.martianTranslate = martianTranslate;
    }

    @Override
    public ArrayList<Character> execute() {
        ArrayList<Character> listCharacters = characterListInterface.execute();

        if (!listCharacters.isEmpty()) {
            listCharacters.stream().forEach((character) -> {
                if (character.getOrigin() != null && character.getOrigin().getName() != null) {
                    if (MARS.equals(character.getOrigin().getName())) {
                        character.setType(martianTranslate.execute(character.getName()));
                    }
                }else{
                    character.setOrigin(new Origin());
                    character.getOrigin().setName("unknown");
                }
            });
            //listCharacters.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            listCharacters = listCharacters.stream().sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName())).collect(Collectors.toCollection(ArrayList::new));

        } else {
            return listCharacters;
        }

        return listCharacters;
    }
}
