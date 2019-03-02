package co.kid.beerpunk.details;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.config.RetrofitConfig;
import co.kid.beerpunk.list.FavoriteBeerListActivity;
import co.kid.beerpunk.list.ListBeerActivity;
import co.kid.beerpunk.listener.BeerListener;
import co.kid.beerpunk.model.BeerDetail;
import co.kid.beerpunk.repository.BeerRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerDetailActivity extends AppCompatActivity implements BeerListener, View.OnClickListener {

    private Toolbar beerDetailToolbar;
    private RetrofitConfig retrofitConfig = new RetrofitConfig();
    private BeerRepository beerRepository;
    final BeerDetail beer = new BeerDetail();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_details);
        beerRepository = new BeerRepository(this);
        int id = getIntent().getExtras().getInt("beerId", -1);
        findViewById(R.id.favorites_button).setOnClickListener(this);
        getBeerDetail(id);
        beerDetailToolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(beerDetailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(this, ListBeerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.favorites:
                intent = new Intent(this, FavoriteBeerListActivity.class);
                return verifyFavorites(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Boolean verifyFavorites(Intent intent) {
        if (beerRepository.getAllIds().size()>0) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        } else {
            Toast.makeText(this, "No favorites yet!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void setViews(BeerDetail beerDetail) {
        ImageView beerImage = findViewById(R.id.beer_image_details);

        TextView beerTitle = findViewById(R.id.beer_title_text);
        TextView beerTagline = findViewById(R.id.beer_tagline);
        TextView beerFirstBrewed = findViewById(R.id.first_brewed_text);
        TextView beerDescription = findViewById(R.id.beer_description_text);
        TextView beerAbv = findViewById(R.id.beer_abv_text);
        TextView beerIbu = findViewById(R.id.beer_ibu_detail_text);
        TextView beerFg = findViewById(R.id.beer_fg_text);
        TextView beerOg = findViewById(R.id.beer_og_text);

        Glide.with(this).load(beerDetail.getImageUrl()).into(beerImage);

        beerTitle.setText(beerDetail.getName());
        beerTagline.setText(beerDetail.getTagline());
        beerFirstBrewed.setText(beerDetail.getFirstBrewed());
        beerDescription.setText(beerDetail.getDescription());
        beerAbv.setText(String.valueOf(beerDetail.getAbv()) + "%");
        beerIbu.setText(String.valueOf(beerDetail.getIbu()));
        beerFg.setText(String.valueOf(beerDetail.getFinalGravity()));
        beerOg.setText(String.valueOf(beerDetail.getOriginalGravity()));
    }

    private void getBeerDetail(int id) {
        Call<List<BeerDetail>> beerDetailCall = retrofitConfig.getPunkApi().getById(id);
        beerDetailCall.enqueue(new Callback<List<BeerDetail>>() {
            @Override
            public void onResponse(Call<List<BeerDetail>> call, Response<List<BeerDetail>> response) {
                beer.setAll(response.body().get(0));
                onBeerDetailAvailable(beer);
            }

            @Override
            public void onFailure(Call<List<BeerDetail>> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }

    @Override
    public void onBeerDetailAvailable(BeerDetail beerDetail) {
        setViews(beerDetail);
        isFavorite();
    }

    private void saveFavorites(View view) {
        Drawable heartOutlinedIcon = getResources().getDrawable(R.drawable.ic_heart_outlined);
        Drawable heartFilleddIcon = getResources().getDrawable(R.drawable.ic_heart_filled);
        if (beerRepository.exists(beer.getId())) {
            beerRepository.deletFavorite(beer.getId());
            view.setBackground(heartOutlinedIcon);
        } else {
            beerRepository.insertFavorites(beer.getName(), beer.getId());
            view.setBackground(heartFilleddIcon);
        }
    }

    private void isFavorite() {
        Drawable heartOutlinedIcon = getResources().getDrawable(R.drawable.ic_heart_outlined);
        Drawable heartFilleddIcon = getResources().getDrawable(R.drawable.ic_heart_filled);
        if (beerRepository.exists(beer.getId())) {
            findViewById(R.id.favorites_button).setBackground(heartFilleddIcon);
        } else {
            findViewById(R.id.favorites_button).setBackground(heartOutlinedIcon);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favorites_button:
                saveFavorites(view);
                break;
        }
    }
}
