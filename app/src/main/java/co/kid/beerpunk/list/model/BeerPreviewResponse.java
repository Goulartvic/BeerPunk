package co.kid.beerpunk.list.model;

import java.util.List;
import java.util.Objects;

public class BeerPreviewResponse {

    public List<BeerPreview> beerPreviewList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerPreviewResponse that = (BeerPreviewResponse) o;
        return Objects.equals(beerPreviewList, that.beerPreviewList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(beerPreviewList);
    }
}
