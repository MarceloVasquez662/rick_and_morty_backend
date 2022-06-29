package cl.mobdev.trainee.rickandmorty.service;

import cl.mobdev.trainee.rickandmorty.gateway.IGetCharacterByIDGateway;
import cl.mobdev.trainee.rickandmorty.gateway.IGetOriginByURLGateway;
import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.stereotype.Service;

@Service
public class Presentation {
    private final IGetCharacterByIDGateway getCharacterByID;
    private final IGetOriginByURLGateway getOriginByURL;

    public Presentation(IGetCharacterByIDGateway getCharacterByID, IGetOriginByURLGateway getOriginByURL) {
        this.getCharacterByID = getCharacterByID;
        this.getOriginByURL = getOriginByURL;
    }

    public Character execute(int id) {
        Character character = getCharacterByID.execute(id);

        /* Logica del metodo */

        return character;
    }
}

