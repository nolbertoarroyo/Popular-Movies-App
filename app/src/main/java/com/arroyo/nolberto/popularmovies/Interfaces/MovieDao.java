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

    @Query("SELECT * FROM favorites")
    LiveData<List<Response.MoviesModel>> loadAllFavorites();

    @Query("SELECT * FROM favorites  WHERE id = :id")
    Response.MoviesModel loadTaskById(int id);


    @Insert()
    void insertFavorite(Response.MoviesModel movieFavorite);


    @Delete
    void deleteFavorite(Response.MoviesModel movieFavorite);

    @Query("DELETE  FROM favorites WHERE id = :id")
    void delete(int id);


}
