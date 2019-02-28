package co.kid.beerpunk.list.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.config.RetrofitConfig;
import co.kid.beerpunk.list.model.Beer;
import co.kid.beerpunk.list.view.adapter.BeerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBeerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BeerAdapter adapter;
    private RetrofitConfig retrofitConfig = new RetrofitConfig();
    private Toolbar beerListToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_beers_activity);
        recyclerView = findViewById(R.id.recyclerBeers);
        layoutManager = new LinearLayoutManager(this);
        beerListToolbar = (Toolbar) findViewById(R.id.beer_list_toolbar);
        setSupportActionBar(beerListToolbar);
        getAllBeers(this);
    }

    private void getAllBeers (final Context context) {
        Call<List<Beer>> call = retrofitConfig.getPunkApi().listPageable(1, 10);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    adapter = new BeerAdapter(context, response.body());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
            }
            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
            }
        });
    }
}
