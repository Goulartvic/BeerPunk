package co.kid.beerpunk.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.config.RetrofitConfig;
import co.kid.beerpunk.model.BeerDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerDetailActivity extends AppCompatActivity {

    private Toolbar beerDetailToolbar;
    private RetrofitConfig retrofitConfig = new RetrofitConfig();
    private BeerDetail beerDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_details);
        int id = getIntent().getExtras().getInt("beerId", -1);
        setViews(getBeerDetail(id));
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
        beerAbv.setText(String.valueOf(beerDetail.getAbv()));
        beerIbu.setText(String.valueOf(beerDetail.getIbu()));
        beerFg.setText(String.valueOf(beerDetail.getFinalGravity()));
        beerOg.setText(String.valueOf(beerDetail.getOriginalGravity()));
    }

    private BeerDetail getBeerDetail(int id) {
        final BeerDetail beer = new BeerDetail();
        Call<List<BeerDetail>> beerDetailCall = retrofitConfig.getPunkApi().getById(id);
        beerDetailCall.enqueue(new Callback<List<BeerDetail>>() {
            @Override
            public void onResponse(Call<List<BeerDetail>> call, Response<List<BeerDetail>> response) {
                beer.setAll(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<BeerDetail>> call, Throwable t) {
            }
        });
        return beer;
    }
}
