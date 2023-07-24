package org.example.CinemaBookingApp.dao.daoImpl;

import org.example.CinemaBookingApp.config.Config;
import org.example.CinemaBookingApp.dao.TheatreDao;
import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.Theatre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheatreDaoImpl implements TheatreDao {
    private Connection connection= Config.getConnection();
    @Override
    public List<Map<Movie, List<Theatre>>> getAllMoviesByTime(int hour) {
        return null;
    }

    @Override
    public void createTableTheatre(String tableName, List<String> columns) {
        StringBuilder stringBuilder=new StringBuilder(String.format("create table %s(",tableName));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                if (i<columns.size()-1){
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(")");
            System.out.println("Successfully saved table :"+tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addTheatre(Theatre theatre) {
        String sql="insert into theatres (name,location)values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,theatre.getName());
            preparedStatement.setString(2,theatre.getLocation());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved theatre");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Theatre searchByTheatreLocation(String location) {
        Theatre theatre=new Theatre();
        String sql="select * from theatres where location=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,location);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                theatre.setId(resultSet.getLong("id"));
                theatre.setName(resultSet.getString("name"));
                theatre.setLocation(resultSet.getString("location"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return theatre;
    }

    @Override
    public void updateTheatreName(String name, String newName) {
        String sql="update theatres set name=? where name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newName);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
            System.out.println("Successfully update theatre name");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTheaterById(Long id) {
        String sql="delete from theatres where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted theatre");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Theatre> getAllTheatres() {
        List<Theatre>theatres=new ArrayList<>();
        String sql="select * from theatres ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                theatres.add(new Theatre(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("location")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return theatres;
    }

    @Override
    public Theatre findTheatreById(Long theatreId) {
        Theatre theatre=new Theatre();
        String sql="select * from theatres where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,theatreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                theatre.setId(resultSet.getLong("id"));
                theatre.setName(resultSet.getString("name"));
                theatre.setLocation(resultSet.getString("location"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return theatre;
    }
}
