package co.kid.beerpunk.list.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerDetail {

    private int id;
    private String name;
    private String tagline;
    private String imageUrl;
    private double abv;
    private double ibu;
    private String firstBrewed;
    private int originalGravity;
    private int finalGravity;
    private double ph;
    private int srm;

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

    public double getPh() {
        return ph;
    }

    public int getSrm() {
        return srm;
    }

    @Override
    public String toString() {
        return "BeerDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", firstBrewed='" + firstBrewed + '\'' +
                ", originalGravity=" + originalGravity +
                ", finalGravity=" + finalGravity +
                ", ph=" + ph +
                ", srm=" + srm +
                '}';
    }
}
