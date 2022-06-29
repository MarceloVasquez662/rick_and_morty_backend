package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.IIsHumanInterface;
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
public class IsHumanTest {

    /*
    Tendrá un método que dado un id,
    usara un gateway que trae un character
    y retorna un "true" si es humano y false en otro caso
    */

    @Mock
    private IIsHumanInterface iIsHumanInterface;
    private IsHumanUseCase isHuman;

    private Character character;

    @BeforeEach
    void setUp(){
        isHuman=new IsHumanUseCase(iIsHumanInterface);
    }

    @Test
    void should_return_true_when_character_is_human(){
        //GIVEN
        int id=1;
        character=new Character();
        character.setSpecies("Human");
        Mockito.when(iIsHumanInterface.getCharacterByID(1)).thenReturn(character);

        //WHEN
        boolean response=isHuman.execute(id);

        //THEN
        assertTrue(response);
    }

    @Test
    void should_return_false_when_character_is_not_human(){
        boolean expected=false;

        //GIVEN
        int id=1;
        character=new Character();
        character.setSpecies("Alien");
        Mockito.when(iIsHumanInterface.getCharacterByID(1)).thenReturn(character);

        //WHEN
        boolean response=isHuman.execute(id);
        //THEN
        assertFalse(response);
    }
}
