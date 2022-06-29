package cl.mobdev.trainee.rickandmorty.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Location {

    private String name;
    private String url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ArrayList<String> residents;

    public ArrayList<String> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<String> residents) {
        this.residents = residents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
