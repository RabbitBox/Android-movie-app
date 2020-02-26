package ukim.finki.mpip2019.myimdb.async_tasks;

import android.os.AsyncTask;
import ukim.finki.mpip2019.myimdb.room.MovieDao;

public class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
    private MovieDao movieDao;

    public DeleteAllAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAll();
        return null;
    }
}
