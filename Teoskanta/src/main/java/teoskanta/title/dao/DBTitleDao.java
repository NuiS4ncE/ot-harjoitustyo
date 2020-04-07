package teoskanta.title.dao;

import java.io.File;
import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import teoskanta.domain.Title;
import teoskanta.domain.User;
import teoskanta.user.dao.UserDao;
import teoskanta.domain.Title;

public class DBTitleDao implements TitleDao<Title, Integer> {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement stat;

    private void startConn() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        stat = connection.createStatement();
    }

    private void closeConn() throws SQLException {
        stat.close();
        stmt.close();
        connection.close();
    }

    public void checkDatabaseFileForTitles() throws Exception {
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
        closeConn();
    }

    public Boolean findTitle(String name, String author, String year, int userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT name, author, year, userid FROM Titles WHERE name = ? AND author = ? AND year = ? AND userid = ?");
        stmt.setString(1, name);
        stmt.setString(2, author);
        stmt.setString(3, year);
        stmt.setInt(4, userid);
        ResultSet rs = stmt.executeQuery();
        String rsName, rsAuthor, rsYear;
        int rsUserid;
        if (!rs.next()) {
            return null;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getString("year");
            rsUserid = rs.getInt("userid");
        }
        if (rsName.equals(name) && rsAuthor.equals(author) && rsYear == year && rsUserid == userid) {
            stmt.close();
            rs.close();
            connection.close();
            return true;
        }
        rs.close();
        closeConn();
        return false;
    }

    public Boolean findTitle(String name, String author, String year) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("SELECT name, author, year FROM Titles WHERE name = ? AND author = ? AND year = ?");
        stmt.setString(1, name);
        stmt.setString(2, author);
        stmt.setString(3, year);
        ResultSet rs = stmt.executeQuery();
        String rsName, rsAuthor, rsYear;
        if (!rs.next()) {
            return null;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getString("year");
        }
        System.out.println(rsName + " SQL " + rsAuthor + " " + rsYear);
        if (rsName.equals(name) && rsAuthor.equals(author) && rsYear == year) {
            stmt.close();
            rs.close();
            connection.close();
            return true;
        }
        rs.close();
        closeConn();
        return false;
    }

    @Override
    public void create(Title title) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("INSERT INTO Titles"
                + "(name, author, year, userid)"
                + "VALUES (?,?,?,?)");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setString(3, title.getYear());
        stmt.setInt(4, title.getUserId());
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
            stmt.close();
            rs.close();
            connection.close();
            return title;
        }
        rs.close();
        closeConn();
        return null;
    }

    @Override
    public Title update(Title object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Title title, Integer id) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("DELETE * FROM Titles"
                + "WHERE id = ? AND userid = ?");
        stmt.setInt(1, id);
        stmt.setInt(2, title.getUserId());
        stmt.executeUpdate();
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
            titleList.add(new Title(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("year"), rs.getInt("userid")));
        }
        closeConn();
        return titleList;
    }

}
