package org.example.CinemaBookingApp.dao;

import org.example.CinemaBookingApp.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingDao {
    String save(Long showTimeId, Long userId, int numberOfTickets, LocalDateTime bookingTime);
    Booking findById(Long bookingId);
    Booking delete(Long bookingId);
    List<Booking>getAllBookings();
    List<Booking> getBookingByUserId(Long userId);
}
