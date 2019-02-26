package co.kid.beerpunk.list.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerPreview {

    private int id;
    private String name;
    private String tagline;
    private String imageUrl;
    private double ibu;

    public BeerPreview(int id, String name, String tagline, String imageUrl) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.imageUrl = imageUrl;
    }

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
        return "BeerPreview{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ibu=" + ibu +
                '}';
    }
}
