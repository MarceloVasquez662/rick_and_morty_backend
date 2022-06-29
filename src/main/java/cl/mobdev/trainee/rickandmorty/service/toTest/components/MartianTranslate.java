package cl.mobdev.trainee.rickandmorty.service.toTest.components;

import org.springframework.stereotype.Component;

@Component
public class MartianTranslate{

    public String execute(String name) {
        if(name==null || name.trim().equals(""))
        {
            return "";
        }
        else{
            String martianName=name;

            martianName=martianName.toLowerCase();
            martianName=martianName.replace(" ","");
            martianName=martianName.replace("a","1");
            martianName=martianName.replace("e","2");
            martianName=martianName.replace("i","3");
            martianName=martianName.replace("o","4");
            martianName=martianName.replace("u","5");
            return martianName;
        }
    }
}
