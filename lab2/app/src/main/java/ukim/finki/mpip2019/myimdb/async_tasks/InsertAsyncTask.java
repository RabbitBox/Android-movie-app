package ukim.finki.mpip2019.myimdb.async_tasks;

import android.os.AsyncTask;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.room.MovieDao;

public class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public InsertAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.insert(movies[0]);
        return null;
    }
}
