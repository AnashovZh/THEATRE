package org.example.CinemaBookingApp.dao.daoImpl;

import org.example.CinemaBookingApp.config.Config;
import org.example.CinemaBookingApp.dao.MovieDao;
import org.example.CinemaBookingApp.enums.Genre;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.User;

import javax.sound.midi.MidiFileFormat;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MovieDaoImpl implements MovieDao {
    private Connection connection = Config.getConnection();

    @Override
    public void createMovie(String tableName, List<String> columns) {
        StringBuilder stringBuilder = new StringBuilder(String.format("create table %s(", tableName));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                if (i < columns.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(")");
            statement.executeUpdate(stringBuilder.toString());
            System.out.println("Successfully created table " + tableName);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addMovie(Movie movie) {
        String sql = "insert into movies (title,genre,duration)values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, String.valueOf(movie.getGenre()));
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Successfully saved person to movies :" + movie.getTitle());
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public List<Movie> searchByName(String title) {
        List<Movie>movies=new ArrayList<>();
        String sql = "select * from movies where title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                movies.add(new Movie(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("duration")));
            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return movies;
    }

    @Override
    public Map<Genre, List<Movie>> getMoviesByGenre(String genre) {
        Map<Genre,List<Movie>>maps=new HashMap<>();
        List<Movie >movies=new ArrayList<>();
        String sql="select * from movies where genre=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
            movies.add(new Movie(resultSet.getLong("id"),
                     resultSet.getString("title"),
                     resultSet.getString("genre"),
                     resultSet.getInt("duration")));
            }
                maps.put(Genre.valueOf(genre),movies);
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maps;
    }

    @Override
    public List<Movie> sortByDuration(String ascOrDesc) {
        List<Movie>movies=new ArrayList<>();
        String sql="select * from movies order by duration  "+ascOrDesc;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                movies.add(new Movie(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("duration")));
            }
            preparedStatement.close();
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime) {
        String sql="select *from movies m join ";
        return null;
    }

    @Override
    public void updateGenre(Long MovieId, String genre) {
        String sql="update movies set genre=? where id= ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(Genre.valueOf(genre)));
            preparedStatement.setLong(2,MovieId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated genre ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Movie findMovieById(Long id) {
        Movie movie=new Movie();
        String sql="select * from movies where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                movie.setId(resultSet.getLong("id"));
                movie.setTitle(resultSet.getString("title"));
               movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
                movie.setDuration(resultSet.getInt("duration"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movie;
    }
}
