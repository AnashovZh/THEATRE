package org.example.CinemaBookingApp.service;

import org.example.CinemaBookingApp.model.User;

import java.util.List;

public interface UserService {
    void createTableUser(String tableName, List<String>columns);
    void addUser(User user);
    User searchByUserName(String userName);
    List<User> sortByUserName(String ascOrDesc);
    void deleteUser(String userName);
    void updateUser(String name,User user);
    List<User>getAllUsers();
    Boolean existByEmail(String email);
    User findUserById(Long id);
}
