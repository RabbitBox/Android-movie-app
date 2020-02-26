package ukim.finki.mpip2019.myimdb.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {
    @SerializedName("Search")
    private List<Movie> search;

    public List<Movie> getList(){
        return search;
    }
}