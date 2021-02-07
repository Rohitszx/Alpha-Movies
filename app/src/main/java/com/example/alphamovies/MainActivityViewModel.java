package com.example.alphamovies;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.alphamovies.Database.MovieRepository;
import com.example.alphamovies.Models.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();
    private LiveData<List<Movie>> favMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        MovieRepository movieRepository = new MovieRepository(application);
        Log.d(TAG, "Retrieving tasks from database via ViewModel");
        favMovies = movieRepository.loadAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favMovies;
    }
}
