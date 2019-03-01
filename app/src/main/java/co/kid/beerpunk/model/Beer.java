package co.kid.beerpunk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer {

    private int id;
    private String name;
    private String tagline;
    @JsonProperty(value = "image_url")
    private String imageUrl;
    private double ibu;

    public Beer(int id, String name, String tagline, String imageUrl, double ibu) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.imageUrl = imageUrl;
        this.ibu = ibu;
    }

    public Beer() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getIbu() {
        return ibu;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ibu=" + ibu +
                '}';
    }
}
