package cl.mobdev.trainee.rickandmorty.service;

import cl.mobdev.trainee.rickandmorty.model.Character;


public interface IGetCharacterByIDUseCase {
     Character execute(int id);
}
