package cl.mobdev.trainee.rickandmorty.service;

import cl.mobdev.trainee.rickandmorty.gateway.IGetCharacterByIDGateway;
import cl.mobdev.trainee.rickandmorty.gateway.IGetOriginByURLGateway;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCharacterByIDUseCaseTest {
    @Mock
    private IGetCharacterByIDGateway getCharacterByID;
    @Mock
    private IGetOriginByURLGateway getOriginByURLGateway;
    private GetCharacterByIDUseCase getCharacterByIDUseCase;

    @BeforeEach
    void setUp(){
        getCharacterByIDUseCase=new GetCharacterByIDUseCase(getCharacterByID, getOriginByURLGateway);
    }

    @Test
    void should_return_character_when_origin_null(){
        Character expectedCharacter=new Character();
        expectedCharacter.setName("Rick");
        expectedCharacter.setOrigin(new Origin());
        expectedCharacter.getOrigin().setName("unknown");

        //GIVEN
        int id=1;
        Character character=new Character();
        character.setName("Rick");

        when(getCharacterByID.execute(id)).thenReturn(character);

        //WHEN
        Character resultCharacter=getCharacterByIDUseCase.execute(id);

        //THEN
        assertEquals(expectedCharacter.getName(), resultCharacter.getName());
        assertEquals(expectedCharacter.getOrigin().getName(), resultCharacter.getOrigin().getName());
    }

    @Test
    void should_return_character_when_origin_url_is_null(){
        Character expectedCharacter=new Character();
        expectedCharacter.setName("Rick");
        expectedCharacter.setOrigin(new Origin());
        expectedCharacter.getOrigin().setName("unknown");

        //GIVEN
        int id=1;
        Character character=new Character();
        character.setName("Rick");
        character.setOrigin(new Origin());
        character.getOrigin().setUrl("");

        when(getCharacterByID.execute(id)).thenReturn(character);

        //WHEN
        Character resultCharacter=getCharacterByIDUseCase.execute(id);

        //THEN
        assertEquals(expectedCharacter.getName(), resultCharacter.getName());
        assertEquals(expectedCharacter.getOrigin().getName(), resultCharacter.getOrigin().getName());
    }

    @Test
    void should_return_character_complete(){
        Character expectedCharacter=new Character();
        expectedCharacter.setName("Rick");
        expectedCharacter.setOrigin(new Origin());
        expectedCharacter.getOrigin().setName("Earth");

        //GIVEN
        int id=1;
        Character character=new Character();
        character.setName("Rick");
        character.setOrigin(new Origin());
        character.getOrigin().setUrl("www.earth.cl");

        Origin origin=new Origin();
        origin.setName("Earth");
        origin.setUrl("www.earth.cl");

        Mockito.when(getCharacterByID.execute(id)).thenReturn(character);
        Mockito.when(getOriginByURLGateway.execute("www.earth.cl")).thenReturn(origin);

        //WHEN
        Character resultCharacter=getCharacterByIDUseCase.execute(id);

        //THEN
        assertEquals(expectedCharacter.getName(), resultCharacter.getName());
        assertEquals(expectedCharacter.getOrigin().getName(), resultCharacter.getOrigin().getName());
    }

    @Test
    void should_verify_origin_gateway_execute(){

        int id=1;
        int wantedInvocations=1;
        String url="www.url.cl";
        //GIVEN
        Origin origin=new Origin();
        origin.setUrl(url);

        Character character=new Character();
        character.setName("Rick");
        character.setEpisode(new ArrayList());
        character.setOrigin(origin);

        Mockito.when(getCharacterByID.execute(id)).thenReturn(character);
        Mockito.when(getOriginByURLGateway.execute(url)).thenReturn(origin);

        //WHEN
        getCharacterByIDUseCase.execute(id);

        //THEN
        verify(getOriginByURLGateway, Mockito.times(wantedInvocations)).execute(url);
    }

    @Test
    void should_verify_origin_gateway_not_execute(){

        int id=1;
        //GIVEN
        Character character=new Character();
        character.setName("Rick");
        character.setEpisode(new ArrayList());

        Mockito.when(getCharacterByID.execute(id)).thenReturn(character);

        //WHEN
        getCharacterByIDUseCase.execute(id);

        //THEN
        verify(getOriginByURLGateway, never()).execute("");
    }

}