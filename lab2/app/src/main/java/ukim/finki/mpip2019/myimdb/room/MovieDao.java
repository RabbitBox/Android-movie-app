package ukim.finki.mpip2019.myimdb.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import ukim.finki.mpip2019.myimdb.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> findAll();

    @Query(value = "DELETE FROM movies")
    void deleteAll();

    @Update
    void update(Movie movie);

    @Query("SELECT * FROM movies WHERE imdbID=:id LIMIT 1")
    LiveData<Movie> findById(String id);
}