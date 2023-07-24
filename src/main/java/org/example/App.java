package org.example;

import org.example.CinemaBookingApp.dao.BookingDao;
import org.example.CinemaBookingApp.dao.daoImpl.BookingDaoImpl;
import org.example.CinemaBookingApp.service.MovieService;
import org.example.CinemaBookingApp.service.ShowTimeService;
import org.example.CinemaBookingApp.service.TheatreService;
import org.example.CinemaBookingApp.service.UserService;
import org.example.CinemaBookingApp.service.serviceImpl.MovieServiceImpl;
import org.example.CinemaBookingApp.service.serviceImpl.ShowTimeServiceImpl;
import org.example.CinemaBookingApp.service.serviceImpl.TheatreServiceImpl;
import org.example.CinemaBookingApp.service.serviceImpl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        Project - "CinemaBookingApp" for JDBC
//        Movie methods:
//        CRUD
//        List<Movie> searchByName(String title);
//        <Map<Genre, List<Movie> getMoviesByGenre(String genre);
//        List<Movie> sortByDuration(String ascOrDesc);
//        List<Movie> getMoviesByTheaterIdAndStartTime(Long theaterId, LocalDate startTime);
//        Theater methods:
//        CRUD
//        List<Map<Movie, List<Theater>>>  getAllMoviesByTime(int hour);  параметирден канча саат берсек. Кинолордун узундугу ошол саатка барабар болгон бардык кинолорду жана театырларын чыгарышы керек!
//        ShowTime methods:
//        String save(ShowTime showtime);
//        String assign(Long showTimeId, Long movieId, Long theaterId);
//        List<ShowTime> getAll();
//        ShowTime findById(Long showTimeId);
//        String deleteShowTimeByStartAndEndTime(LocalDate startTime, LocalDate endTime);
//        List<Map<Theater, List<Movie>>> getMoviesGroupByTheater();
//        User methods:
//        CRUD
//        Boolean existByEmail(String email);
//        Booking methods:
//        String save(new Booking(Long showTimeId, Long userId, int numberOfTickets));
//        findById;
//        delete;
//        getAllBookings();
//        List<Booking> getBookingByUserId(Long userId);
        //**********************************
        // Config.getConnection();

        MovieService movieService = new MovieServiceImpl();
//        movieService.createMovie("movies",List.of("id serial primary key," +
//        "title varchar, genre varchar, duration int"));
//        movieService.addMovie(new Movie("Tom Hard", Genre.ACTION,130));
//        movieService.searchByName("Titanik").forEach(System.out::println);
//        System.out.println(movieService.getMoviesByGenre("ROMANTIC"));
//        movieService.sortByDuration("desc").forEach(System.out::println);
//        movieService.updateGenre(1L,"COMEDY");
//        movieService.sortByDuration("asc").forEach(System.out::println);
//        System.out.println(movieService.findMovieById(1L));

        UserService userService = new UserServiceImpl();
//        userService.createTableUser("users",List.of("id serial primary key," +
//                "user_name varchar,password varchar,email varchar"));
//        userService.addUser(new User("Talgat","Nadyrbekov","zt@gmail.com"));
//        System.out.println(userService.searchByUserName("Kanzada"));
//        userService.sortByUserName("desc").forEach(System.out::println);
//        userService.deleteUser("Amangeldi");
//        userService.updateUser("Zalkar",new User("Arlen","asdf","bk@gmail.com"));
//        userService.getAllUsers().forEach(System.out::println);
//        System.out.println(userService.existByEmail("bsdf@gmail.com"));
//        System.out.println(userService.findUserById(1L));
//-------------------------------------------------------------------------
        TheatreService theatreService = new TheatreServiceImpl();
//        theatreService.createTableTheatre("theatres",List.of("id serial primary key,name varchar,location varchar"));
//    theatreService.addTheatre(new Theatre("Nation","Abdrahmanov 23"));
//        System.out.println(theatreService.searchByTheatreLocation("Abdrahmanov 23"));
//        theatreService.updateTheatreName("Nation","Opera");
//        theatreService.deleteTheaterById(1L);
//        theatreService.getAllTheatres().forEach(System.out::println);
//        userService.createTableUser("show_times",List.of("id serial primary key," +
//                "movie_id int references movies(id),theatre_id int references theatres (id)," +
//                "start_time timestamp,end_time timestamp"));
//        System.out.println(theatreService.findTheatreById(2L));
        //----------------------------------------------------------------
        ShowTimeService showTimeService=new ShowTimeServiceImpl();
//        System.out.println(showTimeService.save(new ShowTime(4L, 3L, LocalDateTime.of(2023, 7, 21,
//                2, 2, 2), LocalDateTime.of(2023, 7, 22, 2, 2, 2))));
//        System.out.println(showTimeService.assign(3L, 2L, 2L));
//        showTimeService.getAll().forEach(System.out::println);
//        System.out.println(showTimeService.deleteShowTimeByStartAndEndTime(LocalDateTime.of(2023, 7, 21, 2, 2, 2), LocalDateTime.of(2023, 7, 22, 2, 2, 2)));
//        LocalDateTime start=LocalDateTime.of(2023,7,21,2,2,2);
//        LocalDateTime endTime = LocalDateTime.of(2023,7,22,2,2,2);
//        System.out.println(showTimeService.deleteShowTimeByStartAndEndTime(start, endTime));
//        showTimeService.getMoviesGroupByTheater().forEach(System.out::println);
        BookingDao bookingDao=new BookingDaoImpl();
//        userService.createTableUser("bookings", List.of("id serial primary key ,show_time_id int references show_times(id)," +
//                "user_id int references users(id),number_of_tickets int,booking_time timestamp"));
//        System.out.println(bookingDao.save(3L, 11L, 99,
//                LocalDateTime.of(2023, 1, 3, 2, 3, 2)));
//        System.out.println(bookingDao.findById(3L));    
//        System.out.println(bookingDao.delete(5L));
//        bookingDao.getAllBookings().forEach(System.out::println);
//        System.out.println(bookingDao.getBookingByUserId(11L));

    }
}