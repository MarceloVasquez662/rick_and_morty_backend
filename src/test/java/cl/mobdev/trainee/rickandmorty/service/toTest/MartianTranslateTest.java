package cl.mobdev.trainee.rickandmorty.service.toTest;

import cl.mobdev.trainee.rickandmorty.service.toTest.components.MartianTranslate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

public class MartianTranslateTest {

    private MartianTranslate martianTranslate;

    @BeforeEach
    void setUp(){
        martianTranslate=new MartianTranslate();
    }

    @Test
    void should_return_martian_name(){
        String expectedName="1l32n";

        //GIVEN
        String name="Alien";

        //WHEN
        String response=martianTranslate.execute(name);

        //THEN
        assertEquals(expectedName, response);
    }

    @Test
    void should_return_empty_name_when_name_empty_or_null(){
        String expectedName="";

        //GIVEN
        String name=null;

        //WHEN
        String response=martianTranslate.execute(name);

        //THEN
        assertEquals(expectedName, response);
    }
}
