package ukim.finki.mpip2019.myimdb.omdb_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ukim.finki.mpip2019.myimdb.models.Movie;
import ukim.finki.mpip2019.myimdb.models.MovieList;

public interface OmdbApiService {

    @GET("/?apikey=e33bbc97")
    Call<Movie> getMovieById(@Query("i") String id);

    @GET("/?apikey=e33bbc97")
    Call<MovieList> getSearchResults(@Query("s") String term);
}
