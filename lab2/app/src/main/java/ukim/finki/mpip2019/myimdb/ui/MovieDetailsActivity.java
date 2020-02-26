package ukim.finki.mpip2019.myimdb.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ukim.finki.mpip2019.myimdb.R;
import ukim.finki.mpip2019.myimdb.adapters.MovieAdapter;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.omdb_api.OmdbApiService;
import ukim.finki.mpip2019.myimdb.omdb_api.RetrofitClientInstance;
import ukim.finki.mpip2019.myimdb.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private OmdbApiService omdbApiCalls;
    private MovieDetailsViewModel movieDetailsViewModel;
    private ImageView ivPoster;
    private TextView tvTitle, tvYear, tvDescription;
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitle = findViewById(R.id.tvTitleDetails);
        tvYear = findViewById(R.id.tvYearDetails);
        tvDescription = findViewById(R.id.tvDescriptionDetails);
        ivPoster = findViewById(R.id.ivPosterDetails);

        omdbApiCalls = RetrofitClientInstance.getRetrofitInstance().create(OmdbApiService.class);

        String movieId = getIntent().getStringExtra(MovieAdapter.MOVIE_ID);
        Log.i(TAG, movieId);
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.setMovie(movieId);
//        movieDetailsViewModel.getMovie().observe(this, new Observer<Movie>() {
//            @Override
//            public void onChanged(@Nullable Movie movie) {
//                Log.i(TAG, movie.getTitle());
//                tvTitle.setText(movie.getTitle());
//                tvYear.setText(movie.getYear());
//                tvDescription.setText(movie.getDescription());
//                Glide.with(getApplicationContext())
//                        .load(movie.getImgUrl())
//                        .centerCrop()
//                        .crossFade()
//                        .into(ivPoster);
//            }
//        });
        omdbApiCalls.getMovieById(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
               // movieDetailsViewModel.update(response.body());
                showOnScreen(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
    public void showOnScreen(Movie movie){
        Log.i(TAG, movie.getTitle());
        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tvDescription.setText(movie.getDescription());
        Glide.with(getApplicationContext())
                .load(movie.getImgUrl())
                .centerCrop()
                .crossFade()
                .into(ivPoster);
    }
}