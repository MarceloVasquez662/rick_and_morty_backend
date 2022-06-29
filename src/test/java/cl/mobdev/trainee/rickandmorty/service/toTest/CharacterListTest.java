package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import cl.mobdev.trainee.rickandmorty.service.toTest.interfaces.ICharacterListInterface;
import cl.mobdev.trainee.rickandmorty.service.toTest.components.MartianTranslate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CharacterListTest {

    /*
    Tendrá un método que no recibe parámetros
    usara un Gateway que trae un listado de characters
    y retornara ese listado de characteres ordenado por nombre,
    pero a los characteres que sean del planeta "Mars",
    se les reescribirá el atributo "type" usando lenguaje marciano
    usando como dato de entrada el nombre.
    Algoritmo lenguaje marciano:
    Dado un texto cada vocal es reemplazada por un numero; a(1), e(2), i(3), o(4), u(5)
    todos los caracteres son minúsculas y sin espacios.
    Por ejemplo, nombre "Hola Mundo" --> "h4l1m5nd4"
     */

    @Mock
    private ICharacterListInterface iCharacterListInterface;
    @Mock
    private MartianTranslate martianTranslate;
    private CharacterListUseCase characterListUseCase;

    @BeforeEach
    void setUp(){
        characterListUseCase=new CharacterListUseCase(iCharacterListInterface, martianTranslate);
    }

    @Test
    void should_return_empty_list_when_list_of_characters_is_empty(){
        ArrayList<Character> expectedList=new ArrayList();

        //GIVEN
        ArrayList<Character> listCharacters=new ArrayList<>();
        Mockito.when(iCharacterListInterface.execute()).thenReturn(listCharacters);

        //THEN
        ArrayList<Character> responseList=characterListUseCase.execute();

        //WHEN
        assertEquals(expectedList, responseList);
    }

    @Test
    void should_return_sort_list_of_characters() {
        ArrayList<Character> expectedOrder = new ArrayList<>();

        //GIVEN
        Character character = new Character();
        Character character2 = new Character();
        Character character3 = new Character();
        character.setName("Angel");
        character2.setName("Morty");
        character3.setName("Rick");

        expectedOrder.add(character);
        expectedOrder.add(character2);
        expectedOrder.add(character3);

        ArrayList<Character> listCharacters=new ArrayList<>();
        listCharacters.add(character3);
        listCharacters.add(character);
        listCharacters.add(character2);

        Mockito.when(iCharacterListInterface.execute()).thenReturn(listCharacters);

        //WHEN
        ArrayList<Character> response=characterListUseCase.execute();

        //THEN
        assertEquals(expectedOrder, response);

        /*for(int i=0; i<expectedOrder.size();i++)
        {
            assertEquals(expectedOrder.get(i).getName(), response.get(i).getName());
        }*/
    }

    @Test
    void should_return_type_when_origin_is_null() {
        String expectedType="Human";

        //GIVEN
        Character character=new Character();
        character.setName("Rick");
        character.setType("Human");

        ArrayList<Character> listCharacters=new ArrayList<>();
        listCharacters.add(character);
        Mockito.when(iCharacterListInterface.execute()).thenReturn(listCharacters);
        //WHEN
        ArrayList<Character> response=characterListUseCase.execute();
        //THEN
        assertEquals(expectedType, response.get(0).getType());
    }

    @Test
    void should_return_type_with_martian_language(){
        String expectedType="1l32n";

        //GIVEN
        Character character=new Character();
        character.setName("Alien");
        character.setOrigin(new Origin());
        character.getOrigin().setName("Mars");

        ArrayList<Character> listCharacters=new ArrayList<>();
        listCharacters.add(character);

        Mockito.when(iCharacterListInterface.execute()).thenReturn(listCharacters);
        Mockito.when(martianTranslate.execute(character.getName())).thenReturn(expectedType);

        //WHEN
        ArrayList<Character> response=characterListUseCase.execute();

        //THEN
        assertEquals(expectedType, response.get(0).getType());
    }

    @Test
    void should_return_type_when_character_not_martian(){
        String expectedType="Human";

        //GIVEN
        Character character=new Character();
        character.setName("Human");
        character.setOrigin(new Origin());
        character.getOrigin().setName("Earth");
        character.setType("Human");

        ArrayList<Character> listCharacters=new ArrayList<>();
        listCharacters.add(character);

        Mockito.when(iCharacterListInterface.execute()).thenReturn(listCharacters);

        //WHEN
        ArrayList<Character> response=characterListUseCase.execute();

        //THEN
        assertEquals(expectedType, response.get(0).getType());
    }

}
