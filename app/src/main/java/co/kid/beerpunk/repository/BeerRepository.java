package co.kid.beerpunk.repository;

import android.content.ContentValues;
import android.content.Context;

import co.kid.beerpunk.config.DatabaseGateway;

public class BeerRepository {

    private final String TABLE_FAVORITE_BEER = "FAVORITE_BEER";
    private DatabaseGateway gateway;

    public BeerRepository(Context ctx){
        gateway = DatabaseGateway.getInstance(ctx);
    }

    public boolean insertFavorites(String name, int beerId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("id", beerId);
        return gateway.getDatabase().insert(TABLE_FAVORITE_BEER, null, contentValues) > 0;
    }
}
