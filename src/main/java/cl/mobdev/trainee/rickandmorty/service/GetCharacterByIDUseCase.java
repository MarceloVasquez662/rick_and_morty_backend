package cl.mobdev.trainee.rickandmorty.service;

import cl.mobdev.trainee.rickandmorty.gateway.IGetCharacterByIDGateway;
import cl.mobdev.trainee.rickandmorty.gateway.IGetOriginByURLGateway;
import cl.mobdev.trainee.rickandmorty.model.Character;
import cl.mobdev.trainee.rickandmorty.model.Origin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GetCharacterByIDUseCase implements IGetCharacterByIDUseCase {
    private final IGetCharacterByIDGateway getCharacterByID;
    private final IGetOriginByURLGateway getOriginByURL;

    public GetCharacterByIDUseCase(IGetCharacterByIDGateway getCharacterByID, IGetOriginByURLGateway getOriginByURL) {
        this.getCharacterByID = getCharacterByID;
        this.getOriginByURL = getOriginByURL;
    }

    @Override
    public Character execute(int id) {
        Character character = getCharacterByID.execute(id);

        if(character.getEpisode()!=null)
        {
            character.setEpisodeCounts(character.getEpisode().size());
        }else{
            character.setEpisodeCounts(0);
        }

        if (character.getOrigin() == null || character.getOrigin().getUrl().equals("")) {
            character.setOrigin(new Origin());
            character.getOrigin().setName("unknown");
            character.getOrigin().setUrl("");
            character.getOrigin().setResidents(new ArrayList());
            character.getOrigin().setDimension("");
        } else {
            character.setOrigin(getOriginByURL.execute(character.getOrigin().getUrl()));
        }

        return character;
    }
}
