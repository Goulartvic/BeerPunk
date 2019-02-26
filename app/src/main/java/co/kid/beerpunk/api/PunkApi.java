package co.kid.beerpunk.api;

import co.kid.beerpunk.list.model.Beer;
import co.kid.beerpunk.list.model.BeerPreview;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PunkApi {

    @GET("beers")
    Call<BeerPreview> getPageable(
            @Query("page") Long page,
            @Query("per_page") Long perPage
    );

    @GET("beers/{id}")
    Call<Beer> getById(@Path("id") Long id);
}
