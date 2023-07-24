package org.example.CinemaBookingApp.dao.daoImpl;

import org.example.CinemaBookingApp.config.Config;
import org.example.CinemaBookingApp.dao.UserDao;
import org.example.CinemaBookingApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection= Config.getConnection();
    @Override
    public void createTableUser(String tableName, List<String> columns) {
    StringBuilder sql=new StringBuilder(String.format("create table %s(", tableName));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                sql.append(columns.get(i));
                if (i<columns.size()-1){
                    sql.append(", ");
                }
            }
            sql.append(")");
            statement.executeUpdate(sql.toString());
            System.out.println("Successfully created table: "+tableName );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) {
        String sql="insert into users (user_name,password,email)values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved user");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User searchByUserName(String userName) {
        User user =new User();
        String sql="select * from users where user_name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> sortByUserName(String ascOrDesc) {
        List<User>users=new ArrayList<>();
        String sql="select * from users order by user_name "+ascOrDesc;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void deleteUser(String userName) {
        String sql="delete from users where user_name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            System.out.println(searchByUserName(userName));
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(String name, User user) {

        String sql="update users set user_name=?,password=?,email=? where user_name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4,name);
            System.out.println("Successfully update user: "+searchByUserName(name)+" to "+user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User>users=new ArrayList<>();
        String sql="select * from users ";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public Boolean existByEmail(String email) {
        Boolean exists=false;

        String sql="select case when exists (select from users where email=?) then 'false' else  'true' end";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
//            preparedStatement.setString(2,"true");
//            preparedStatement.setString(3,"false");
            ResultSet resultSet = preparedStatement.executeQuery();
           exists=resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return exists;
    }

    @Override
    public User findUserById(Long id) {
        User user=new User();
        String sql="select * from users where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
