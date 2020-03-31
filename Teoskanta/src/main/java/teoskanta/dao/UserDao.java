package teoskanta.dao;

import teoskanta.domain.User;
import java.util.*;
import java.sql.*;

public class UserDao implements Dao<User, Integer> {

    @Override
    public void create(User user) throws SQLException {
        Connection connection = DriverManager.getConnection("./database.db");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
                + "(username, password)"
                + "VALUES (?,?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public User read(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("./database.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        User u = new User(rs.getString("username"), rs.getString("password"));

        stmt.close();
        rs.close();
        connection.close();

        return u;
    }

    public Boolean findUser(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT username, password FROM Users WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        String rsUserName;
        String rsPassword;
        if (!rs.next()) {
            return null;
        } else {
            rsUserName = rs.getString("username");
            rsPassword = rs.getString("password");
        }
        System.out.println(rsUserName + " SQL " + rsPassword);
        if(rsUserName.equals(username) && rsPassword.equals(password)){
            return true;
        }
        //User u = new User(rs.getString("username"), rs.getString("password"));
        stmt.close();
        rs.close();
        connection.close();

        return false;
    }

    @Override
    public User update(User object) throws SQLException {
        // ei toteutettu
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

    @Override
    public List<User> list() throws SQLException {
        // ei toteutettu
        return null;
    }
}
