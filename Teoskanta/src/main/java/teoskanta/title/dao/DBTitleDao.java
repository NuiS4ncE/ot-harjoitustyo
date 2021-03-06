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
 * Class to handle SQL queries.
 *
 */
public class DBTitleDao implements TitleDao<Title, Integer> {

    private Connection connection;
    private PreparedStatement stmt;
    private Statement stat;

    /**
     * Sets up connection to db.
     *
     * @throws SQLException
     */
    private void startConn() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        stat = connection.createStatement();
    }

    /**
     * Closes connection to db.
     *
     * @throws SQLException
     */
    private void closeConn() throws SQLException {
        stat.close();
        connection.close();
    }
    
    /**
     * Checks database if titles table exists.
     * @throws Exception 
     */
    public void checkDBForTitles() throws Exception {
        // check if database file exists
        String titleTable = "CREATE TABLE IF NOT EXISTS Titles ("
                + "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "`name` TEXT,"
                + "`author` TEXT,"
                + "`year` TEXT,"
                + "`category` TEXT,"
                + "`userid` INTEGER);";
        startConn();
        stat.executeUpdate(titleTable);
        System.out.println("A new database table for titles has been created.");
        stat.close();
        connection.close();
    }
    
    /**
     * Method for finding title in the database.
     * @param title Title-type variable for title input
     * @param userid int-type variable for userid input
     * @return returns true if title in db matches the title-object
     * @throws SQLException 
     */
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
    
    /**
     * Method for finding title from database without userid.
     * @param title Title-type variable for title input
     * @return returns true if title in database matches the given title-object
     * @throws SQLException 
     */
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
    
    /**
     * Method for adding title to database.
     * @param title Title-type variable for title input
     * @param userid int-type variable for userid input
     * @throws SQLException 
     */
    @Override
    public void create(Title title, Integer userid) throws SQLException {
        startConn();
        stmt = connection.prepareStatement("INSERT INTO Titles"
                + "(name, author, year, category, userid)"
                + "VALUES (?,?,?,?,?)");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setString(3, title.getYear());
        stmt.setString(4, title.getCategory());
        stmt.setInt(5, userid);
        stmt.executeUpdate();
        closeConn();
    }
    
    /**
     * Method for checking database for titles. DEPRECATED
     * @param title Title-type variable for title input
     * @param userid int-type variable for userid input
     * @return returns null
     * @throws SQLException 
     */
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
    /**
     * Method for deleting title from database.
     * @param title Title-type variable for title input
     * @param userid int-type variable for userid input
     * @throws SQLException 
     */
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
    
    /**
     * Method for listing titles from database.
     * @param userid int-type variable for userid input
     * @return returns a List
     * @throws SQLException 
     */
    @Override
    public List<Title> list(Integer userid) throws SQLException {
        startConn();
        List<Title> titleList = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM Titles WHERE userid = ?");
        stmt.setInt(1, userid);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            titleList.add(new Title(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("year"), rs.getString("category"), rs.getInt("userid")));
        }
        stmt.close();
        closeConn();
        return titleList;
    }
    
    /**
     * Method for retrieving categories for a list. 
     * @param userid int-type variable for userid input
     * @return returns a List
     * @throws SQLException 
     */
    public List<String> getCategoryList(Integer userid) throws SQLException {
        startConn();
        List<String> categoryList = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM Titles WHERE userid = ? GROUP BY category");
        stmt.setInt(1, userid);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            categoryList.add(rs.getString("category"));
        }
        stmt.close();
        closeConn();
        return categoryList;
    }
    
    /**
     * Method for getting a List by category.
     * @param userid int-type variable for userid input
     * @param category String-type variable for category input
     * @return returns a List
     * @throws SQLException 
     */
    public List<Title> getListByCategory(Integer userid, String category) throws SQLException {
        startConn();
        List<Title> categoryList = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM Titles WHERE userid = ? AND category = ?");
        stmt.setInt(1, userid);
        stmt.setString(2, category);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            categoryList.add(new Title(rs.getString("name"), rs.getString("author"), rs.getString("year"), rs.getString("category"), userid));
        }
        stmt.close();
        closeConn();
        return categoryList;
    }
}
