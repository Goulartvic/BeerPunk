package co.kid.beerpunk.list.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.kid.beerpunk.R;
import co.kid.beerpunk.list.model.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private Context context;
    private List<Beer> beers;

    public BeerAdapter(Context context, List<Beer> beers) {
        this.context = context;
        this.beers = beers;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_beer, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        final Beer beer = beers.get(position);
        holder.beerName.setText(beer.getName());
        holder.beerIbu.setText(String.valueOf(beer.getIbu()));
        Glide.with(context).load(beer.getImageUrl()).into(holder.beerImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder {
        ImageView beerImage;
        TextView beerName;
        TextView beerIbu;

        public BeerViewHolder(View itemView) {
            super(itemView);
            beerImage= (ImageView) itemView.findViewById(R.id.imageBeer);
            beerName = (TextView) itemView.findViewById(R.id.textNameBeer);
            beerIbu = itemView.findViewById(R.id.beer_ibu);
        }
    }
}
