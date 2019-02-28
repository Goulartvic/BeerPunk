package co.kid.beerpunk.config;


import co.kid.beerpunk.api.PunkApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        this.retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public PunkApi getPunkApi() {
        return this.retrofit.create(PunkApi.class);
    }
}
