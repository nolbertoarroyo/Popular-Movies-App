package com.arroyo.nolberto.popularmovies.Interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.arroyo.nolberto.popularmovies.Model.Response;

import java.util.List;
@Dao
public interface MovieDao {

    //selecting all favorites in database

    @Query("SELECT * FROM favorites")
    LiveData<List<Response.MoviesModel>> loadAllFavorites();

    //retrieving movie by id
    @Query("SELECT * FROM favorites  WHERE id = :id")
    Response.MoviesModel loadTaskById(int id);

    //inserting movie object into database
    @Insert()
    void insertFavorite(Response.MoviesModel movieFavorite);

    //using query to delete item in database by id
    @Query("DELETE  FROM favorites WHERE id = :id")
    void delete(int id);


}
