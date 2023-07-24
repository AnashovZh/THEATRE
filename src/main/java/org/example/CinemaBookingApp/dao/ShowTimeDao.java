package org.example.CinemaBookingApp.dao;

import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.ShowTime;
import org.example.CinemaBookingApp.model.Theatre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ShowTimeDao {
    String save(ShowTime showtime);
    String assign(Long showTimeId, Long movieId, Long theaterId);
    List<ShowTime> getAll();
    ShowTime findById(Long showTimeId);
    String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
    List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater();
     void assign(ShowTime showTime);

}
