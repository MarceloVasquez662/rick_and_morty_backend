package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.exception.GenderException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.IIsFemaleInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IsFemaleTest {

/*
    Tendrá un método que dado un id,
    usara un gateway que trae un character
    y retorna el Character si es "female"
    o lanza una GenderException en otro caso
    con un mensaje que dice "El character no es mujer"

*/
    @Mock
    private IIsFemaleInterface isFemaleInterface;
    private IsFemaleUseCase isFemaleUseCase;

    private Character character;

    @BeforeEach
    void setUp(){
        isFemaleUseCase=new IsFemaleUseCase(isFemaleInterface);
    }

    @Test
    void should_return_character_when_character_is_female(){
        String expectedGender="Female";

        //GIVEN
        int id=1;
        character=new Character();
        character.setGender("Female");
        Mockito.when(isFemaleInterface.getFemaleCharacter(1)).thenReturn(character);

        //WHEN
        Character response=isFemaleUseCase.execute(id);

        //THEN
        assertEquals(expectedGender, response.getGender());
    }

    @Test
    void should_return_exception_when_character_is_not_female(){

        String expectedMessage="El character no es mujer";
        //GIVEN
        int id=1;
        character=new Character();
        character.setGender("Male");
        Mockito.when(isFemaleInterface.getFemaleCharacter(id)).thenReturn(character);

        //WHEN
        GenderException thrown=assertThrows(GenderException.class, ()->isFemaleUseCase.execute(id));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    void should_return_exception_when_character_gender_is_null(){

        String expectedMessage="El character no es mujer";
        //GIVEN
        int id=1;
        character=new Character();
        Mockito.when(isFemaleInterface.getFemaleCharacter(id)).thenReturn(character);

        //WHEN
        GenderException thrown=assertThrows(GenderException.class, ()->isFemaleUseCase.execute(id));
        //THEN
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
