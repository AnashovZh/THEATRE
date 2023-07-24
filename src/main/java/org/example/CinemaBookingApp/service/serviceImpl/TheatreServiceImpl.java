package org.example.CinemaBookingApp.service.serviceImpl;

import org.example.CinemaBookingApp.dao.TheatreDao;
import org.example.CinemaBookingApp.dao.daoImpl.TheatreDaoImpl;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.Theatre;
import org.example.CinemaBookingApp.service.TheatreService;

import java.util.List;
import java.util.Map;

public class TheatreServiceImpl implements TheatreService {
    private TheatreDao theatreDao = new TheatreDaoImpl();

    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        return theatreDao.getAllMoviesByTime(hour);
    }

    @Override
    public void createTableTheatre(String tableName, List<String> columns) {
        theatreDao.createTableTheatre(tableName, columns);
    }

    @Override
    public void addTheatre(Theatre theatre) {
        theatreDao.addTheatre(theatre);
    }

    @Override
    public Theatre searchByTheatreLocation(String location) {
        return theatreDao.searchByTheatreLocation(location);
    }

    @Override
    public void updateTheatreName(String name, String newName) {
        theatreDao.updateTheatreName(name, newName);
    }

    @Override
    public void deleteTheaterById(Long id) {
        theatreDao.deleteTheaterById(id);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreDao.getAllTheatres();
    }

    @Override
    public Theatre findTheatreById(Long theatreId) {
        return theatreDao.findTheatreById(theatreId);
    }
}
