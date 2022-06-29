package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.stereotype.Repository;

@Repository
public interface IGetCharacterByIDGateway {
    Character execute(int id);
}
