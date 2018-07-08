package com.arroyo.nolberto.popularmovies.Utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.arroyo.nolberto.popularmovies.Interfaces.MovieDao;
import com.arroyo.nolberto.popularmovies.Model.Response;

@Database(entities = Response.MoviesModel.class, version = 1, exportSchema = false)
public abstract class FavoritesDatabase extends RoomDatabase {
    private static FavoritesDatabase databaseInstance;
    private final static Object Lock = new Object();

    public static FavoritesDatabase getDbInstance(Context context) {

        if (databaseInstance == null) {
            synchronized (Lock) {

                databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FavoritesDatabase.class, Constants.FavoritesDatabaseName).allowMainThreadQueries().build();

            }

        }
        return databaseInstance;

    }
    public abstract MovieDao movieDao();
}
