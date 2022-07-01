package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import cl.mobdev.trainee.rickandmorty.service.toTest.CharacterIsFromEarthUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class CharacterIsFromEarthTest {

    private CharacterIsFromEarthUseCase characterIsFromEarthUseCase;

    private Origin origin;
    private Character character;

    @BeforeEach
    void setUp(){
        characterIsFromEarthUseCase=new CharacterIsFromEarthUseCase();

    }

    @Test
    void should_be_true_if_character_is_from_earth() {
        boolean expected = true;

        //GIVEN
        character=new Character();
        origin=new Origin();

        origin.setName("Earth");
        character.setOrigin(origin);

        //THEN
        boolean response=characterIsFromEarthUseCase.execute(character);

        //WHEN
        Assertions.assertEquals(expected, response);
    }

    @Test
    void should_be_false_if_character_is_from_another_planet(){
        boolean expected=false;

        //GIVEN
        character=new Character();
        origin=new Origin();

        origin.setName("Jupiter");
        character.setOrigin(origin);

        //WHEN
        boolean response=characterIsFromEarthUseCase.execute(character);

        //THEN
        Assertions.assertEquals(expected, response);
    }

    @Test
    void should_be_false_if_origin_is_null(){

        //GIVEN
        character=new Character();
        character.setName("Rick");

        //WHEN
        boolean result= characterIsFromEarthUseCase.execute(character);

        //THEN
        assertFalse(result);
    }

    @Test
    void should_be_false_if_origin_name_is_null(){

        //GIVEN
        character=new Character();
        character.setName("Rick");
        character.setOrigin(new Origin());


        //WHEN
        boolean result= characterIsFromEarthUseCase.execute(character);

        //THEN
        assertFalse(result);
    }
}
