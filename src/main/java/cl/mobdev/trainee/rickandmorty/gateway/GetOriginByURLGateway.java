package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.model.Origin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetOriginByURLGateway implements IGetOriginByURLGateway {

    private final RestTemplate restTemplate;

    public GetOriginByURLGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Origin execute(String uri) {

         ResponseEntity<Origin> response= restTemplate.getForEntity(uri, Origin.class);
         Origin origin=response.getBody();

        return origin;
    }
}
