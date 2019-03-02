package co.kid.beerpunk.listener;

import co.kid.beerpunk.model.BeerDetail;

public interface BeerListener {

    void onBeerDetailAvailable(BeerDetail beerDetail);
}
