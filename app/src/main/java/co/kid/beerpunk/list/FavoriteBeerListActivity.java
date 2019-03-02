package co.kid.beerpunk.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.config.RetrofitConfig;
import co.kid.beerpunk.list.ListBeerActivity;
import co.kid.beerpunk.list.adapter.BeerAdapter;
import co.kid.beerpunk.model.Beer;
import co.kid.beerpunk.repository.BeerRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteBeerListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BeerAdapter adapter;
    private RetrofitConfig retrofitConfig = new RetrofitConfig();
    private Toolbar beerListToolbar;
    private List<Beer> beers = new ArrayList<>();
    private List<Integer> favoritesId = new ArrayList<>();
    private BeerRepository beerRepository;
    private Integer pageIndex = 1;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_beers_activity);
        beerRepository = new BeerRepository(this);
        recyclerView = findViewById(R.id.recyclerBeers);
        layoutManager = new LinearLayoutManager(this);
        beerListToolbar = findViewById(R.id.list_toolbar);
        beerListToolbar.setTitle("Favorites");
        setSupportActionBar(beerListToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buildView();
        getBeersPageable(pageIndex);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ListBeerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.allBeer:
                intent = new Intent(this, ListBeerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorites_toolbar_menu, menu);
        return true;
    }

    private void buildView () {
        adapter = new BeerAdapter(this, beers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    getBeersPageable(pageIndex);
                }
            }
        });
    }

    private void getBeersPageable(final Integer page) {
        List<Integer> ids = beerRepository.getAllIds();
        StringBuilder pathParam = new StringBuilder();
        for (int id: ids) {
            pathParam.append(id+"|");
        }
        Call<List<Beer>> call = retrofitConfig.getPunkApi().listFavoritesPageable(page, 10, pathParam.toString());
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                for (Beer beer: response.body()) {
                    beers.add(beer);
                }
                pageIndex++;
                adapter.setData(beers);
            }
            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
            }
        });
    }
}
