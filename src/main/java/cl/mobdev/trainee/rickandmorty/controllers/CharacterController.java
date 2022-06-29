package cl.mobdev.trainee.rickandmorty.controllers;

import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.service.IGetCharacterByIDUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/character")
public class CharacterController {

    private final IGetCharacterByIDUseCase iGetCharacterByIDUseCase;

    public CharacterController(IGetCharacterByIDUseCase iGetCharacterByIDUseCase) {
        this.iGetCharacterByIDUseCase = iGetCharacterByIDUseCase;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Character> getData(@PathVariable int id){
        return ResponseEntity.ok(iGetCharacterByIDUseCase.execute(id));
    }

}
