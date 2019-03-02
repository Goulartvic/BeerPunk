package co.kid.beerpunk.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import co.kid.beerpunk.config.DatabaseGateway;

public class BeerRepository {

    private final String TABLE_FAVORITE_BEER = "FAVORITE_BEER";
    private DatabaseGateway gateway;

    public BeerRepository(Context ctx){
        gateway = DatabaseGateway.getInstance(ctx);
    }

    public boolean insertFavorites(String name, int beerId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("beer_name", name);
        contentValues.put("beer_id", beerId);
        return gateway.getDatabase().insert(TABLE_FAVORITE_BEER, null, contentValues) > 0;
    }

    public boolean deletFavorite(int id) {
        return gateway.getDatabase().delete(TABLE_FAVORITE_BEER, "beer_id="+id, null) > 0;
    }

    public boolean exists(int id) {
        String query = "SELECT * FROM " + TABLE_FAVORITE_BEER + " where beer_id = " + id;
        Cursor cursor = gateway.getDatabase().rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<Integer> getAllIds() {
        List<Integer> idList = new ArrayList<>();
        String query = "SELECT beer_id FROM " + TABLE_FAVORITE_BEER;
        Cursor cursor = gateway.getDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            idList.add(cursor.getInt(0));
        }
        return idList;
    }
}
