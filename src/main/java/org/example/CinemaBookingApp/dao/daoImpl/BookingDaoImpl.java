package org.example.CinemaBookingApp.dao.daoImpl;

import org.example.CinemaBookingApp.config.Config;
import org.example.CinemaBookingApp.dao.BookingDao;
import org.example.CinemaBookingApp.model.Booking;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {
    private final Connection connection= Config.getConnection();

    @Override
    public String save(Long showTimeId, Long userId, int numberOfTickets, LocalDateTime bookingTime) {
        String sql="insert into bookings(show_time_id,user_id,number_of_tickets,booking_time)values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,showTimeId);
            preparedStatement.setLong(2,userId);
            preparedStatement.setInt(3,numberOfTickets);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(bookingTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Successfully saved";
    }

    @Override
    public Booking findById(Long bookingId) {
        Booking booking=new Booking();
        String sql="select * from bookings where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,bookingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                booking.setId(resultSet.getLong("id"));
                booking.setShowTimeId(resultSet.getLong("show_time_id"));
                booking.setUserId(resultSet.getLong("user_id"));
                booking.setNumberOfTickets(resultSet.getInt("number_of_tickets"));
                booking.setBookingTime(resultSet.getTimestamp("show_time").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

    @Override
    public Booking delete(Long bookingId) {
        Booking booking=new Booking();
        String sql="delete from bookings where id=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,bookingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                booking.setId(resultSet.getLong("id"));
                booking.setShowTimeId(resultSet.getLong("booking_time_id"));
                booking.setUserId(resultSet.getLong("user_id"));
                booking.setNumberOfTickets(resultSet.getInt("number_of_tickets"));
                booking.setBookingTime(resultSet.getTimestamp("booking_time").toLocalDateTime());
            }
            preparedStatement.close();
            System.out.println("Successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking>bookings=new ArrayList<>();
        String sql="select * from bookings ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bookings.add(new Booking(resultSet.getLong("id"),
                        resultSet.getLong("show_time_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getInt("number_of_tickets"),
                        resultSet.getTimestamp("booking_time").toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        List<Booking>bookings=new ArrayList<>();
        String sql="select * from bookings  b join users u on b.user_id=u.id where u.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bookings.add(new Booking(resultSet.getLong("id"),
                        resultSet.getLong("show_time_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getInt("number_of_tickets"),
                        resultSet.getTimestamp("booking_time").toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookings;
    }
}