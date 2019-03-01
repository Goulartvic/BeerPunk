package co.kid.beerpunk.api;

import java.util.List;

import co.kid.beerpunk.model.BeerDetail;
import co.kid.beerpunk.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PunkApi {

    @GET("beers")
    Call<List<Beer>> listPageable(
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("beers/{ids}/favorite")
    Call<List<Beer>> listFavoritesPageable(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Path("ids") String ids
    );

    @GET("beers/{id}")
    Call<List<BeerDetail>> getById(@Path("id") int id);
}
