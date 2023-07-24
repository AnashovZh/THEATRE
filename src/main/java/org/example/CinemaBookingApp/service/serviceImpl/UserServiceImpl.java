package org.example.CinemaBookingApp.service.serviceImpl;

import org.example.CinemaBookingApp.dao.UserDao;
import org.example.CinemaBookingApp.dao.daoImpl.UserDaoImpl;
import org.example.CinemaBookingApp.model.User;
import org.example.CinemaBookingApp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao=new UserDaoImpl();

    @Override
    public void createTableUser(String tableName, List<String> columns) {
        userDao.createTableUser(tableName,columns);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);

    }

    @Override
    public User searchByUserName(String userName) {
        return userDao.searchByUserName(userName);
    }

    @Override
    public List<User> sortByUserName(String ascOrDesc) {
        return userDao.sortByUserName(ascOrDesc);
    }

    @Override
    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }

    @Override
    public void updateUser(String name, User user) {
        userDao.updateUser(name,user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Boolean existByEmail(String email) {
        return userDao.existByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }
}
