package cl.mobdev.trainee.rickandmorty.controllers;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.IGetCharacterByIDUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    @Mock
    private IGetCharacterByIDUseCase iGetCharacterByIDUseCase;

    private CharacterController characterController;

    private Character character =new Character();

    @BeforeEach
    void setUp(){
        characterController=new CharacterController(iGetCharacterByIDUseCase);
    }

    @Test
    void should_return_status_200(){
        int statusExpected=200;
        //GIVEN
        Character character=new Character();
        character.setName("Rick");

        Mockito.when(iGetCharacterByIDUseCase.execute(1)).thenReturn(character);

        //WHEN
        ResponseEntity<Character> responseCharacter=characterController.getData(1);

        //THEN
        assertEquals(statusExpected,responseCharacter.getStatusCodeValue());
    }
}