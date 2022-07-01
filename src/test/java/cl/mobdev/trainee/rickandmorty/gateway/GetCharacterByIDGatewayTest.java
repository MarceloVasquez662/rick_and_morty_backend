package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.exception.CharacterNotFoundException;
import cl.mobdev.trainee.rickandmorty.exception.GatewayException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetCharacterByIDGatewayTest {

    private GetCharacterByIDGateway getCharacterByIDGateway;

    @Mock
    private RestTemplate restTemplate;

    private String url="www.url.cl";

    @BeforeEach
    void setUp(){
        getCharacterByIDGateway=new GetCharacterByIDGateway(url, restTemplate);
    }

    @Test
    void should_return_character_when_call_api(){
        Character characterExpected=new Character();
        characterExpected.setName("Rick");
        characterExpected.setSpecies("Human");

        //GIVEN
        ResponseEntity<Character> character=new ResponseEntity<>(new Character(),HttpStatus.OK);
        character.getBody().setName("Rick");
        character.getBody().setSpecies("Human");

        Mockito.when(restTemplate.getForEntity(url+1, Character.class)).thenReturn(character);

        //WHEN
        Character result= getCharacterByIDGateway.execute(1);

        //THEN
        verify(restTemplate).getForEntity(url+1, Character.class);
        assertEquals(characterExpected.getName(), result.getName());
        assertEquals(characterExpected.getSpecies(), result.getSpecies());
    }

    @Test
    void should_throw_exception_when_character_not_found_in_api(){
        String messageExpected="Character not found in API";

        //GIVEN
        Mockito.when(restTemplate.getForEntity(url+2,Character.class)).thenThrow(HttpClientErrorException.class);

        //WHEN
        CharacterNotFoundException thrown=assertThrows(CharacterNotFoundException.class, ()->getCharacterByIDGateway.execute(2));

        //THEN
        assertEquals(messageExpected, thrown.getMessage());
    }

    @Test
    void should_throw_exception_when_api_error(){
        String messageExpected="Character Gateway Error";

        //GIVEN
        Mockito.when(restTemplate.getForEntity(url+1,Character.class)).thenThrow(RestClientException.class);

        //WHEN
        GatewayException thrown=assertThrows(GatewayException.class, ()->getCharacterByIDGateway.execute(1));

        //THEN
        assertEquals(messageExpected, thrown.getMessage());
    }
}