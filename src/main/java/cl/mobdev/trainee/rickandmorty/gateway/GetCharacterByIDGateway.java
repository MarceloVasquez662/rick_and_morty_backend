package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.exception.CharacterNotFoundException;
import cl.mobdev.trainee.rickandmorty.exception.GatewayException;
import cl.mobdev.trainee.rickandmorty.model.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class GetCharacterByIDGateway implements IGetCharacterByIDGateway {

    private final String url;
    private final RestTemplate restTemplate;

    public GetCharacterByIDGateway(@Value("${url}")String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Character execute(int id) {
        Character character=null;
        try {
            ResponseEntity<Character> response = restTemplate.getForEntity(url + id, Character.class);
            character = response.getBody();

        } catch(HttpClientErrorException ex){
            throw new CharacterNotFoundException("Character not found in API");
        } catch (RestClientException ex) {
            throw new GatewayException("Character Gateway Error");
        }

        return character;
    }
}
