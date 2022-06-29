package cl.mobdev.trainee.rickandmorty.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"id","name","status","species","type","episode_counts","origin"})
public class Character{

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String gender;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ArrayList episode;
    @JsonProperty("episode_counts")
    private int episodeCounts;
    private Origin origin;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList episode) {
        this.episode = episode;
    }

    public int getEpisodeCounts() {
        return episodeCounts;
    }

    public void setEpisodeCounts(int episodeCounts) {
        this.episodeCounts = episodeCounts;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

}
