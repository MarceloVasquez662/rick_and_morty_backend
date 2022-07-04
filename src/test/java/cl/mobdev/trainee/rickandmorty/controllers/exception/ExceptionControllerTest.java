package cl.mobdev.trainee.rickandmorty.controllers.exception;

import cl.mobdev.trainee.rickandmorty.exception.CharacterNotFoundException;
import cl.mobdev.trainee.rickandmorty.exception.GatewayException;
import cl.mobdev.trainee.rickandmorty.model.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionControllerTest {

    private ExceptionController exceptionController;

    @BeforeEach
    void setUp(){
        exceptionController=new ExceptionController();
    }

    @Test
    void should_return_status_500_when_runtimeError_throw(){
        int statusExpected=500;

        ResponseEntity<Response> thrown=exceptionController.runtimeError(new RuntimeException());

        assertEquals(statusExpected, thrown.getStatusCodeValue());
    }

    @Test
    void should_return_status_404_when_characterNotFound_throw(){
        int statusExpected=404;

        ResponseEntity<Response> thrown=exceptionController.characterNotFound(new CharacterNotFoundException(""));

        assertEquals(statusExpected, thrown.getStatusCodeValue());
    }

    @Test
    void should_return_status_400_when_gatewayError_throw(){
        int statusExpected=400;

        ResponseEntity<Response> thrown=exceptionController.gatewayError(new GatewayException(""));

        assertEquals(statusExpected, thrown.getStatusCodeValue());
    }

}