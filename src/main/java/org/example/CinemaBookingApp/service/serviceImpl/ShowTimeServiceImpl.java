package org.example.CinemaBookingApp.service.serviceImpl;

import org.example.CinemaBookingApp.dao.ShowTimeDao;
import org.example.CinemaBookingApp.dao.daoImpl.ShowTimeDaoImpl;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.ShowTime;
import org.example.CinemaBookingApp.model.Theatre;
import org.example.CinemaBookingApp.service.ShowTimeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShowTimeServiceImpl implements ShowTimeService {
    private ShowTimeDao showTimeDao=new ShowTimeDaoImpl();
    @Override
    public String save(ShowTime showtime) {
        return showTimeDao.save(showtime);
    }

    @Override
    public String assign(Long showTimeId, Long movieId, Long theaterId) {
        return showTimeDao.assign(showTimeId,movieId,theaterId);
    }

    @Override
    public List<ShowTime> getAll() {
        return showTimeDao.getAll();
    }

    @Override
    public ShowTime findById(Long showTimeId) {
        return showTimeDao.findById(showTimeId);
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        return showTimeDao.deleteShowTimeByStartAndEndTime(startTime,endTime);
    }

    @Override
    public List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater() {
        return showTimeDao.getMoviesGroupByTheater();
    }

    @Override
    public void assign(ShowTime showTime) {
        showTimeDao.assign(showTime);
    }
}
