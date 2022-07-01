package cl.mobdev.trainee.rickandmorty.controllers;

import cl.mobdev.trainee.rickandmorty.controllers.exception.ExceptionController;
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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    @Mock
    private IGetCharacterByIDUseCase iGetCharacterByIDUseCase;

    private CharacterController characterController;

    @BeforeEach
    void setUp(){
        characterController=new CharacterController(iGetCharacterByIDUseCase);
    }

    @Test
    void should_return_status_200_when_execute_successfully(){
        int statusExpected=200;

        //GIVEN
        Mockito.when(iGetCharacterByIDUseCase.execute(1)).thenReturn(new Character());

        //WHEN
        ResponseEntity<Character> responseCharacter=characterController.getData(1);

        //THEN
        assertEquals(statusExpected,responseCharacter.getStatusCodeValue());
    }

    @Test
    void should_verify_api_call_is_execute(){
        int numberOfInvocations=1;

        //GIVEN
        Mockito.when(iGetCharacterByIDUseCase.execute(1)).thenReturn(new Character());

        //WHEN
        iGetCharacterByIDUseCase.execute(1);

        //THEN
        verify(iGetCharacterByIDUseCase, atLeastOnce()).execute(1);
    }
}