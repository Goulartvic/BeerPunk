package co.kid.beerpunk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerDetail {

    private int id;
    private String name;
    private String tagline;
    private String description;
    @JsonProperty(value = "image_url")
    private String imageUrl;
    private double abv;
    private double ibu;
    @JsonProperty(value = "first_brewed")
    private String firstBrewed;
    @JsonProperty(value = "target_og")
    private int originalGravity;
    @JsonProperty(value = "target_fg")
    private int finalGravity;

    public BeerDetail() {
    }

    public void setAll(BeerDetail beerDetail) {
        this.id = beerDetail.getId();
        this.name = beerDetail.getName();
        this.tagline = beerDetail.getTagline();
        this.description = beerDetail.getDescription();
        this.imageUrl = beerDetail.getImageUrl();
        this.abv = beerDetail.getAbv();
        this.ibu = beerDetail.getIbu();
        this.firstBrewed = beerDetail.getFirstBrewed();
        this.originalGravity = beerDetail.getOriginalGravity();
        this.finalGravity = beerDetail.getFinalGravity();
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

    public double getAbv() {
        return abv;
    }

    public double getIbu() {
        return ibu;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public int getOriginalGravity() {
        return originalGravity;
    }

    public int getFinalGravity() {
        return finalGravity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BeerDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", firstBrewed='" + firstBrewed + '\'' +
                ", originalGravity=" + originalGravity +
                ", finalGravity=" + finalGravity +
                '}';
    }
}
