package org.example.CinemaBookingApp.dao.daoImpl;

import org.example.CinemaBookingApp.config.Config;
import org.example.CinemaBookingApp.dao.MovieDao;
import org.example.CinemaBookingApp.dao.ShowTimeDao;
import org.example.CinemaBookingApp.dao.TheatreDao;
import org.example.CinemaBookingApp.enums.Genre;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.ShowTime;
import org.example.CinemaBookingApp.model.Theatre;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowTimeDaoImpl implements ShowTimeDao {
    private Connection connection= Config.getConnection();
    private MovieDao movieDao=new MovieDaoImpl();
    private TheatreDao theatreDao=new TheatreDaoImpl();
    @Override
    public String save(ShowTime showtime) {
        String sql="insert into show_times (movie_id,theatre_id,start_time,end_time)values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
       preparedStatement.setLong(1,showtime.getMovieId());
       preparedStatement.setLong(2,showtime.getTheatreId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(showtime.getStartTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(showtime.getEndTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Successfully saved showTime";
    }

    @Override
    public String assign(Long showTimeId, Long movieId, Long theaterId) {
        ShowTime showTime=findById(showTimeId);
        Movie movie=movieDao.findMovieById(movieId);
        Theatre theatre =theatreDao.findTheatreById(theaterId);
        showTime.setMovieId(movie.getId());
        showTime.setTheatreId(theatre.getId());
        ShowTimeDaoImpl showTimeDao=new ShowTimeDaoImpl();
        showTimeDao.assign(showTime);
        return "Successfully assigned";
    }

    @Override
    public List<ShowTime> getAll() {
        List<ShowTime>showTimes=new ArrayList<>();
        String sql="select * from show_times ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                showTimes.add(new ShowTime(resultSet.getLong("id"),
                        resultSet.getLong("movie_id"),
                        resultSet.getLong("theatre_id"),
                        resultSet.getTimestamp("start_time").toLocalDateTime(),
                        resultSet.getTimestamp("end_time").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return showTimes;
    }

    @Override
    public ShowTime findById(Long showTimeId) {
        ShowTime showTime=new ShowTime();
        String sql="select * from show_times where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,showTimeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                showTime.setId(resultSet.getLong("id"));
                showTime.setMovieId(resultSet.getLong("movie_id"));
                showTime.setTheatreId(resultSet.getLong("theatre_id"));
                showTime.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                showTime.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
            }
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return showTime;
    }

    @Override
    public String deleteShowTimeByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        String sql="delete from show_times where start_time=? and end_time=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(startTime));
            preparedStatement.setTimestamp(2,Timestamp.valueOf(endTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Successfully deleted showTime";
    }

    @Override
    public List<Map<Theatre, List<Movie>>> getMoviesGroupByTheater() {
        List<Map<Theatre,List<Movie>>>maps=new ArrayList<>();
        String sql="select * from show_times s join theatres t on " +
                "s.theatre_id=t.id join movies m on s.movie_id=m.id ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maps.add(Map.of(new Theatre(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("location")),
                        List.of(new Movie(resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("genre"),
                                resultSet.getInt("duration")))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maps;
    }

    @Override
    public void assign(ShowTime showTime) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    update show_times set movie_id=?,theatre_id=? where id=?;
                    """);
            preparedStatement.setLong(1,showTime.getMovieId());
            preparedStatement.setLong(2,showTime.getTheatreId());
            preparedStatement.setLong(3,showTime.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
