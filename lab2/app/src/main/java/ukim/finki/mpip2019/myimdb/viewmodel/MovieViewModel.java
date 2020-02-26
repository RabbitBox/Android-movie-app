package ukim.finki.mpip2019.myimdb.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import ukim.finki.mpip2019.myimdb.async_tasks.DeleteAllAsyncTask;
import ukim.finki.mpip2019.myimdb.async_tasks.InsertAsyncTask;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.room.MovieDao;
import ukim.finki.mpip2019.myimdb.room.MovieDatabase;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
        movies = movieDao.findAll();
    }

    public void insert(Movie movie) {
        new InsertAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}