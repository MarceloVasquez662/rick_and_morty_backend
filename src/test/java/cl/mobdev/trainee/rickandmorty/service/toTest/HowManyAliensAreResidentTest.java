package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HowManyAliensAreResidentTest {

    // Asumiendo que el objeto 'Character' tiene un campo 'Location'
    // y este a su vez tiene un campo con un array de 'residents'
    // Tendrá un método que dado un listado de Characters,
    // retorna un entero con el total de residentes de las locaciones de los caracteres de la especie "Alien"


    private HowManyAliensAreResident howManyAliensAreResident;

    @BeforeEach
    void setUp(){
        howManyAliensAreResident=new HowManyAliensAreResident();
    }

    @Test
    void should_return_count_of_aliens_are_resident(){

        int expectedCount=2;

        //GIVEN
        ArrayList <String> residents=new ArrayList<>();
        residents.add("Resident 1");
        residents.add("Resident 2");

        Character character=new Character();
        character.setSpecies("Alien");
        character.setLocation(new Location());
        character.getLocation().setResidents(residents);

        ArrayList<Character> listCharacters=new ArrayList<>();
        listCharacters.add(character);

        //WHEN
        int responseCount=howManyAliensAreResident.execute(listCharacters);

        //THEN
        assertEquals(expectedCount, responseCount);
    }


}
