package cl.mobdev.trainee.rickandmorty.service.toTest.interfaces;

import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface ICharacterListInterface {

    ArrayList<Character> execute();
}
