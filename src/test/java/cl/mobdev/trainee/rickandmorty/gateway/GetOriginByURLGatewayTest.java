package cl.mobdev.trainee.rickandmorty.gateway;

import cl.mobdev.trainee.rickandmorty.model.Origin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetOriginByURLGatewayTest {


    private GetOriginByURLGateway getOriginByURLGateway;
    @Mock
    private RestTemplate restTemplate;

    private String url="www.url.cl";

    @BeforeEach
    void setUp(){
        getOriginByURLGateway=new GetOriginByURLGateway(restTemplate);
    }

    @Test
    void should_return_origin_when_call_api() {
        Origin originExpected=new Origin();
        originExpected.setName("Earth");

        //GIVEN
        ResponseEntity<Origin> origin= new ResponseEntity<>(new Origin(),HttpStatus.OK);
        origin.getBody().setName("Earth");

        Mockito.when(restTemplate.getForEntity(url, Origin.class)).thenReturn(origin);

        //WHEN
        Origin result=getOriginByURLGateway.execute(url);

        //THEN
        assertEquals(originExpected.getName(), result.getName());
    }
}