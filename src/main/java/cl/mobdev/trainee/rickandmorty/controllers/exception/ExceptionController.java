package cl.mobdev.trainee.rickandmorty.controllers.exception;

import cl.mobdev.trainee.rickandmorty.exception.CharacterNotFoundException;
import cl.mobdev.trainee.rickandmorty.exception.GatewayException;
import cl.mobdev.trainee.rickandmorty.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionController {
    private Response response;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runtimeError(RuntimeException ex, WebRequest wr){
        response=new Response();
        response.setCode(500);
        response.setData(ex.getMessage());
        response.setRequest(wr.getDescription(false));
        response.setStatus("Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<Response> characterNotFound(CharacterNotFoundException ex, WebRequest wr){
        response=new Response();
        response.setCode(404);
        response.setData(ex.getMessage());
        response.setRequest(wr.getDescription(false));
        response.setStatus("Error");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GatewayException.class)
    public ResponseEntity<Response> gatewayError(GatewayException ex, WebRequest wr){
        response=new Response();
        response.setCode(400);
        response.setData(ex.getMessage());
        response.setRequest(wr.getDescription(false));
        response.setStatus("Error");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
