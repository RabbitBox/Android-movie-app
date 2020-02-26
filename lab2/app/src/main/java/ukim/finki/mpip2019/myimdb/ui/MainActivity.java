package ukim.finki.mpip2019.myimdb.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ukim.finki.mpip2019.myimdb.R;
import ukim.finki.mpip2019.myimdb.adapters.MovieAdapter;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.models.MovieList;
import ukim.finki.mpip2019.myimdb.omdb_api.OmdbApiService;
import ukim.finki.mpip2019.myimdb.omdb_api.RetrofitClientInstance;
import ukim.finki.mpip2019.myimdb.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private OmdbApiService omdbApiService;
    private MovieViewModel movieViewModel;
    private RecyclerView movieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MovieAdapter movieAdapter = new MovieAdapter(this);
        movieRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        omdbApiService = RetrofitClientInstance.getRetrofitInstance().create(OmdbApiService.class);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    movieAdapter.setMovies(movies);
                }
            }
        });

    }

    public void syncData(String term){
        omdbApiService.getSearchResults(term).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieViewModel.deleteAll();
                if(response.isSuccessful()) {
                    for (Movie movie : response.body().getList()) {
                        movieViewModel.insert(movie);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_menu, menu);
        // Get the SearchView and set the searchable configuration
        searchView = (SearchView) menu.findItem(R.id.menu_item1).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                syncData(s.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }


}
