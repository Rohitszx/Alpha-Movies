package com.example.alphamovies.Database;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.alphamovies.Models.Movie;
import com.example.alphamovies.Utils.AppExecutors;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private AppExecutors appExecutors;

    private LiveData<List<Movie>> movies;

    public MovieRepository(Application application) {
        movieDao = MovieDatabase.getInstance(application).movieDao();
        movies = movieDao.loadAllFavoriteMovies();

        appExecutors = AppExecutors.getExecutorInstance();
    }

    public LiveData<List<Movie>> loadAllFavoriteMovies() {
        return movies;
    }

    public boolean isFavorite(int movieId) {
        return movieDao.isFavorite(movieId);
    }

    public void updateFavoriteMovie(final int movieId, final boolean isFavorite) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.updateFavoriteMovie(movieId, isFavorite);
            }
        });
    }

    public void addMovieToFavorites(final Movie movie) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.insertFavoriteMovie(movie);
            }
        });
    }

    public void deleteFavoriteMovie(final Movie movie) {
        appExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDao.deleteFavoriteMovie(movie);
            }
        });
    }
}
