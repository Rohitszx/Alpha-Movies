package com.example.alphamovies;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.alphamovies.Database.MovieRepository;
import com.example.alphamovies.Models.Movie;

public class DetailsActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public DetailsActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }
    public boolean isFavorite(int movieId) {
        return movieRepository.isFavorite(movieId);
    }

    public void addMovieToFavorites(Movie movie) {
        movieRepository.addMovieToFavorites(movie);
    }

    public void removeMovieFromFavorites(Movie movie) {
        movieRepository.deleteFavoriteMovie(movie);
    }

    public void updatFavoriteMovie(int movieId, boolean isFavorite) {
        movieRepository.updateFavoriteMovie(movieId, isFavorite);
    }
}
