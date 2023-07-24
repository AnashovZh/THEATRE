package org.example.CinemaBookingApp.dao;

import org.example.CinemaBookingApp.model.Movie;
import org.example.CinemaBookingApp.model.Theatre;

import java.util.List;
import java.util.Map;

public interface TheatreDao {
    List<Map<Movie, List<Theatre>>>  getAllMoviesByTime(int hour);//параметирден канча саат берсек. Кинолордун узундугу ошол саатка барабар болгон бардык кинолорду жана театырларын чыгарышы керек!
    void  createTableTheatre(String tableName,List<String >columns);
    void addTheatre(Theatre theatre);
   Theatre searchByTheatreLocation(String location);
   void updateTheatreName(String name,String newName);
   void deleteTheaterById(Long id);
   List<Theatre> getAllTheatres();
   Theatre findTheatreById(Long theatreId);
}
