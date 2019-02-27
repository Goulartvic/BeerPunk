package co.kid.beerpunk.list.viewModel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import co.kid.beerpunk.list.model.BeerDetail;

public class ListBeerViewModel {

    private ObservableArrayList<BeerDetail> beerDetails = new ObservableArrayList<>();
    private ObservableBoolean loading = new ObservableBoolean();
    private ObservableField<String> message = new ObservableField<>();
}
