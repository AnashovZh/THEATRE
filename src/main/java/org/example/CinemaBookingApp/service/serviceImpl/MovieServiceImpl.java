package org.example.CinemaBookingApp.service.serviceImpl;

import org.example.CinemaBookingApp.dao.MovieDao;
import org.example.CinemaBookingApp.dao.daoImpl.MovieDaoImpl;
import org.example.CinemaBookingApp.enums.Genre;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.service.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao=new MovieDaoImpl();

    @Override
    public void createMovie(String tableName, List<String> columns) {
        movieDao.createMovie(tableName,columns);
    }

    @Override
    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }

    @Override
    public List<Movie> searchByName(String title) {
        return movieDao.searchByName(title);
    }

    @Override
    public Map<Genre, List<Movie>> getMoviesByGenre(String genre) {
        return movieDao.getMoviesByGenre(genre);
    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        return movieDao.sortByDuration(ascOrDesc);
    }

    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime) {
        return null;
    }

    @Override
    public void updateGenre(Long MovieId, String genre) {
        movieDao.updateGenre(MovieId,genre);
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieDao.findMovieById(id);
    }
}
