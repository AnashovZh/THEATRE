package org.example.CinemaBookingApp.service;

import org.example.CinemaBookingApp.enums.Genre;
import org.example.CinemaBookingApp.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovieService {
    void createMovie(String tableName, List<String > columns);
   void addMovie(Movie movie);
        List<Movie> searchByName(String title);
        Map<Genre, List<Movie>>getMoviesByGenre(String genre);//<Map<Genre, List<Movie> getMoviesByGenre(String genre);
        List<Movie> sortByDuration(String ascOrDesc);
        List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime);
        void updateGenre(Long MovieId,String genre);
        Movie findMovieById(Long id);
}
