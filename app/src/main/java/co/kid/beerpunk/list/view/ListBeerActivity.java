package co.kid.beerpunk.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.config.RetrofitConfig;
import co.kid.beerpunk.list.model.Beer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBeerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllBeers();
        setContentView(R.layout.list_beers_activity);
        recyclerView = findViewById(R.id.recyclerBeers);
        layoutManager = new LinearLayoutManager(this);


    }

    private List<Beer> getAllBeers () {
        final List<Beer> beers = new ArrayList<>();
        Call<List<Beer>> call = retrofitConfig.getPunkApi().listPageable(1, 10);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                for (Beer beerResponse: response.body()) {
                    beers.add(beerResponse);
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                System.out.println("deu ruim");
            }
        });
        return beers;
    }

}
