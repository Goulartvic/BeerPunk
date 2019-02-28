package co.kid.beerpunk.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConfig extends SQLiteOpenHelper {
    private static final String DB_NAME = "punk.db";
    private static final String TABLE = "livros";
    private static final String ID = "beer_id";
    private static final String NAME = "beer_name";
    private static final int VERSION = 1;

    public DatabaseConfig(Context context){
        super(context, DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABLE +"("
                + ID + " integer primary key,"
                + NAME + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
