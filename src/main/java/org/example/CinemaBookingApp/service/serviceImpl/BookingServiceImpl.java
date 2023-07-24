package org.example.CinemaBookingApp.service.serviceImpl;

import org.example.CinemaBookingApp.dao.BookingDao;
import org.example.CinemaBookingApp.dao.daoImpl.BookingDaoImpl;
import org.example.CinemaBookingApp.model.Booking;
import org.example.CinemaBookingApp.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private BookingDao bookingDao=new BookingDaoImpl();

    @Override
    public String save(Long showTimeId, Long userId, int numberOfTickets, LocalDateTime bookingTime) {
        return bookingDao.save(showTimeId,userId,numberOfTickets,bookingTime);
    }

    @Override
    public Booking findById(Long bookingId) {
        return bookingDao.findById(bookingId);
    }

    @Override
    public Booking delete(Long bookingId) {
        return bookingDao.delete(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @Override
    public List<Booking> getBookingByUserId(Long userId) {
        return bookingDao.getBookingByUserId(userId);
    }
}
