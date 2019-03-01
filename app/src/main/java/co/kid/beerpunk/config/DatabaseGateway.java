package co.kid.beerpunk.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseGateway {
    private static DatabaseGateway gateway;
    private SQLiteDatabase database;

    private DatabaseGateway(Context ctx){
        DatabaseConfig config = new DatabaseConfig(ctx);
        database = config.getWritableDatabase();
    }

    public static DatabaseGateway getInstance(Context ctx){
        if(gateway == null)
            gateway = new DatabaseGateway(ctx);
        return gateway;
    }

    public SQLiteDatabase getDatabase(){
        return this.database;
    }
}
