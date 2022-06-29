package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;

import java.util.ArrayList;

public class HowManyAliensAreResident {

    private String ALIEN = "Alien";

    public int execute(ArrayList<Character> listCharacters) {

        return listCharacters.stream()
                .filter(character -> ALIEN.equals(character.getSpecies()))
                .reduce(0,(accumulator, character) -> accumulator+character.getLocation().getResidents().size(), Integer::sum );
    }
}
