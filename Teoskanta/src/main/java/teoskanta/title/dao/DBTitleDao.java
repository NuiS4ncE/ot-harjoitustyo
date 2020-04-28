package teoskanta.title.dao;

import java.io.File;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import teoskanta.title.Title;
import teoskanta.user.User;
import teoskanta.user.dao.UserDao;
import teoskanta.title.Title;

/**
 * Class to handle SQL querys
 * 
 */
public class DBTitleDao implements TitleDao<Title, Integer> {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement stat;
    
    /**
     * Sets up connection to db
     * @throws SQLException 
     */
    private void startConn() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        stat = connection.createStatement();
    }
    
    /**
     * Closes connection to db
     * @throws SQLException 
     */
    private void closeConn() throws SQLException {
        stat.close();
        connection.close();
    }

    public void checkDBForTitles() throws Exception {
        // check if database file exists
        String titleTable = "CREATE TABLE IF NOT EXISTS Titles ("
                + "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "`name` TEXT,"
                + "`author` TEXT,"
                + "`year` TEXT,"
                + "`userid` INTEGER);";
        startConn();
        stat.executeUpdate(titleTable);
        System.out.println("A new database table for titles has been created.");
        stat.close();
        connection.close();
    }

    public Boolean findTitle(Title title, int userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT name, author, year, userid FROM Titles WHERE name = ? AND author = ? AND year = ? AND userid = ?");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setString(3, title.getYear());
        stmt.setInt(4, userid);
        ResultSet rs = stmt.executeQuery();
        String rsName, rsAuthor, rsYear;
        int rsUserid;
        if (!rs.next()) {
            return false;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getString("year");
            rsUserid = rs.getInt("userid");
        }
        if (rsName.equals(title.getName()) && rsAuthor.equals(title.getAuthor()) && rsYear.equals(title.getYear()) && rsUserid == userid) {
            rs.close();
            closeConn();
            return true;
        } else {
            rs.close();
            stmt.close();
            closeConn();
            return false;
        }
    }

    public Boolean findTitle(Title title) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT name, author, year FROM Titles WHERE name = ? AND author = ? AND year = ?");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setString(3, title.getYear());
        ResultSet rs = stmt.executeQuery();
        String rsName, rsAuthor, rsYear;
        if (!rs.next()) {
            return false;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getString("year");
        }
        if (rsName.equals(title.getName()) && rsAuthor.equals(title.getAuthor()) && rsYear.equals(title.getYear())) {
            rs.close();
            stmt.close();
            closeConn();
            return true;
        } else {
            rs.close();
            stmt.close();
            closeConn();
            return false;
        }
    }

    @Override
    public void create(Title title, Integer userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("INSERT INTO Titles"
                + "(name, author, year, userid)"
                + "VALUES (?,?,?,?)");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setString(3, title.getYear());
        stmt.setInt(4, userid);
        stmt.executeUpdate();
        closeConn();
    }

    @Override
    public Title read(Title title, Integer userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT id FROM Titles WHERE id = ? AND userid = ?");
        stmt.setInt(1, title.getId());
        stmt.setInt(2, userid);
        ResultSet rs = stmt.executeQuery();
        int rsId;
        int rsUserid;
        if (!rs.next()) {
            return null;
        } else {
            rsId = rs.getInt("id");
            rsUserid = rs.getInt("userid");
        }
        if (rsId == title.getId() && rsUserid == userid) {
            rs.close();
            closeConn();
            return title;
        }
        rs.close();
        stmt.close();
        closeConn();
        return null;
    }

    @Override
    public Title update(Title object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Title title, Integer userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("DELETE FROM Titles WHERE id = ? AND userid = ?");
        stmt.setInt(1, title.getId());
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        closeConn();
    }

    @Override
    public List<Title> list(Integer userid) throws SQLException {
        startConn();
        List<Title> titleList = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM Titles WHERE userid = ?");
        stmt.setInt(1, userid);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            titleList.add(new Title(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("year"), rs.getInt("userid")));
        }
        stmt.close();
        closeConn();
        return titleList;
    }

}
