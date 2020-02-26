package ukim.finki.mpip2019.myimdb.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import ukim.finki.mpip2019.myimdb.async_tasks.UpdateAsyncTask;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.room.MovieDao;
import ukim.finki.mpip2019.myimdb.room.MovieDatabase;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<Movie> movie;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
    }

    public void setMovie(String movieId){
        movie = movieDao.findById(movieId);
    }

    public void update(Movie movie){
        new UpdateAsyncTask(movieDao).execute(movie);
    }

    public LiveData<Movie> getMovie(){
        return movie;
    }

}