package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.exception.LocationException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Location;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.ICharacterListInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharacterLiveInOriginTest {

    /*
    Tendrá un método que dado un Character,
    retorna un true si donde vive es el mismo lugar de su origen
    y false en cualquier otro caso.
    Lanzara un LocationException si su origen es desconocido
    Lanzara un LocationException si su locación es desconocido
    Lanzara un LocationException si su origen y locación son desconocido
    */

    private CharacterLiveInOriginUseCase characterLiveInOriginUseCase;

    private Character character;
    private Origin origin;
    private Location location;

    @BeforeEach
    void setUp(){
        characterLiveInOriginUseCase=new CharacterLiveInOriginUseCase();
    }

    @Test
    void should_return_true_when_character_live_in_origin(){
        boolean expected=true;

        //GIVEN
        character=new Character();
        origin=new Origin();
        location=new Location();

        origin.setName("Earth");
        location.setName("Earth");
        character.setLocation(location);
        character.setOrigin(origin);
        //WHEN
        boolean response= characterLiveInOriginUseCase.execute(character);
        //THEN
        assertEquals(expected, response);
    }

    @Test
    void should_return_false_when_character_not_live_in_origin(){
        boolean expected=false;

        //GIVEN
        character=new Character();
        origin=new Origin();
        location=new Location();

        origin.setName("Mars");
        location.setName("Earth");
        character.setLocation(location);
        character.setOrigin(origin);
        //WHEN
        boolean response= characterLiveInOriginUseCase.execute(character);
        //THEN
        assertEquals(expected, response);
    }

    @Test
    void should_throw_exception_when_origin_is_unknown(){
        LocationException expectedException=new LocationException("");
        String expectedMessage="Origin unknown";
        //GIVEN
        character=new Character();
        origin=new Origin();
        location=new Location();

        origin.setName("unknown");
        location.setName("Earth");
        character.setOrigin(origin);
        character.setLocation(location);
        //WHEN
        LocationException thrown=assertThrows(expectedException.getClass(), ()-> characterLiveInOriginUseCase.execute(character));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    void should_throw_exception_when_location_is_unknown(){
        LocationException expectedException=new LocationException("");
        String expectedMessage="Location unknown";
        //GIVEN
        character=new Character();
        origin=new Origin();
        location=new Location();

        origin.setName("Earth");
        location.setName("unknown");
        character.setOrigin(origin);
        character.setLocation(location);
        //WHEN
        LocationException thrown=assertThrows(expectedException.getClass(), ()-> characterLiveInOriginUseCase.execute(character));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    void should_throw_exception_when_location_and_origin_is_unknown(){
        LocationException expectedException=new LocationException("");
        String expectedMessage="Origin and location unknown";
        //GIVEN
        character=new Character();
        origin=new Origin();
        location=new Location();

        origin.setName("unknown");
        location.setName("unknown");
        character.setOrigin(origin);
        character.setLocation(location);
        //WHEN
        LocationException thrown=assertThrows(expectedException.getClass(), ()-> characterLiveInOriginUseCase.execute(character));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    void should_throw_exception_when_location_and_origin_is_null(){
        LocationException expectedException=new LocationException("");
        String expectedMessage="Origin and location unknown";

        //GIVEN
        character=new Character();
        character.setName("Rick");

        //WHEN
        LocationException thrown=assertThrows(expectedException.getClass(), ()->characterLiveInOriginUseCase.execute(character));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
