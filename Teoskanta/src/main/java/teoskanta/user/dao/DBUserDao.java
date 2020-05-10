package teoskanta.user.dao;

import java.io.File;
import teoskanta.user.User;
import java.util.*;
import java.sql.*;

public class DBUserDao implements UserDao<User, Integer> {

    private Connection connection;
    private Statement stat;
    private PreparedStatement stmt;

    /**
     * Sets up connection to db
     *
     * @throws SQLException
     */
    private void startConn() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        stat = connection.createStatement();
    }

    /**
     * Closes connection to db
     *
     * @throws SQLException
     */
    private void closeConn() throws SQLException {
        stat.close();
        stmt.close();
        connection.close();
    }

    /**
     * Checks that database file and table exists
     */
    public void checkDBFile() {
        // check if database file exists
        String userTable = "CREATE TABLE IF NOT EXISTS Users ("
                + "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "`username` TEXT NOT NULL,"
                + "`password` TEXT NOT NULL);";
        //String db = "jdbc:sqlite:database.db";            
        try {
            startConn();
            stat = connection.createStatement();
            stat.executeUpdate(userTable);
            System.out.println("A new database has been created or checked.");
            closeConn();
        } catch (Exception e) {
            System.out.println("Database check produced an error: " + e);
        }
    }

    /**
     * Checks database for userid
     *
     * @param username String-type input username
     * @param password String-type input password
     * @return returns userid
     * @throws SQLException
     */
    public int getUserIdFromDB(String username, String password) throws SQLException {
        int id;
        startConn();
        stmt = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return 0;
        }

        id = rs.getInt("id");

        rs.close();
        closeConn();
        return id;
    }

    /**
     * Creates user to database
     *
     * @param user User-type input
     * @throws SQLException
     */
    @Override
    public void create(User user) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("INSERT INTO Users"
                + "(username, password)"
                + "VALUES (?,?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        stmt.executeUpdate();
        closeConn();
    }

    @Override
    public User read(Integer key) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        User u = new User(rs.getString("username"), rs.getString("password"));

        rs.close();
        closeConn();

        return u;
    }

    /**
     * Method to find user from database
     *
     * @param username String-type variable username
     * @param password String-type variable password
     * @return returns true if given input trings are same as received strings
     * from database false if not
     * @throws SQLException
     */
    public Boolean findUser(String username, String password) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT username, password FROM Users WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        String rsUserName, rsPassword;
        if (!rs.next()) {
            return false;
        } else {
            rsUserName = rs.getString("username");
            rsPassword = rs.getString("password");
        }
        System.out.println(rsUserName + " SQL " + rsPassword);
        if (rsUserName.equals(username) && rsPassword.equals(password)) {
            rs.close();
            closeConn();
            return true;
        } else {
            rs.close();
            closeConn();
            return false;
        }
    }

    @Override
    public User update(User object) throws SQLException {
        // ei toteutettu
        return null;
    }

    @Override
    public void delete(User object, Integer key) throws SQLException {
        // ei toteutettu
    }

    @Override
    public List<User> list(Integer key) throws SQLException {
        // ei toteutettu
        return null;
    }
}
