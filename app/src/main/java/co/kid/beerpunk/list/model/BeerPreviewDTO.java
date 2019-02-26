package co.kid.beerpunk.list.model;

public class BeerPreviewDTO {

    private int id;
    private String name;
    private String tagline;
    private String pictureUrl;

    public BeerPreviewDTO (int id, String name, String tagline, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.pictureUrl = pictureUrl;
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

    public String getPictureUrl() {
        return pictureUrl;
    }
}
