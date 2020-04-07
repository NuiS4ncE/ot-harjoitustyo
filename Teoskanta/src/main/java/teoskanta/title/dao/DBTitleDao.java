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

    public void checkDatabaseFileForTitles() throws Exception {
        // check if database file exists
        String titleTable = "CREATE TABLE Titles ("
                + "`id`	INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "`name` TEXT NOT NULL,"
                + "`author` TEXT NOT NULL,"
                + "`year` INTEGER);";
        String db = "jdbc:sqlite:database.db";
        String dbFile = "database.db";
        File file = new File(dbFile);
        Statement stmt;
        if (file.exists()) {
            System.out.println("Title database already exists. All is well!");
        } else {
            Connection conn = DriverManager.getConnection(db);
            stmt = conn.createStatement();
            stmt.executeUpdate(titleTable);
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new database table for titles has been created.");
            stmt.close();
            conn.close();
        }
    }

    public Boolean findTitle(String name, String author, int year, int userid) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT name, author, year, userid FROM Titles WHERE name = ? AND author = ? AND year = ? AND userid = ?");
        stmt.setString(1, name);
        stmt.setString(2, author);
        stmt.setInt(3, year);
        stmt.setInt(4, userid);
        ResultSet rs = stmt.executeQuery();

        String rsName;
        String rsAuthor;
        int rsYear;
        int rsUserid;
        if (!rs.next()) {
            return null;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getInt("year");
            rsUserid = rs.getInt("userid");
        }
        System.out.println(rsName + " SQL " + rsAuthor + " " + rsYear + " " + rsUserid);
        if (rsName.equals(name) && rsAuthor.equals(author) && rsYear == year && rsUserid == userid) {
            stmt.close();
            rs.close();
            connection.close();
            return true;
        }

        stmt.close();
        rs.close();
        connection.close();

        return false;
    }

    public Boolean findTitle(String name, String author, int year) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT name, author, year FROM Titles WHERE name = ? AND author = ? AND year = ?");
        stmt.setString(1, name);
        stmt.setString(2, author);
        stmt.setInt(3, year);
        ResultSet rs = stmt.executeQuery();

        String rsName;
        String rsAuthor;
        int rsYear;
        if (!rs.next()) {
            return null;
        } else {
            rsName = rs.getString("name");
            rsAuthor = rs.getString("author");
            rsYear = rs.getInt("year");
        }
        System.out.println(rsName + " SQL " + rsAuthor + " " + rsYear);
        if (rsName.equals(name) && rsAuthor.equals(author) && rsYear == year) {
            stmt.close();
            rs.close();
            connection.close();
            return true;
        }

        stmt.close();
        rs.close();
        connection.close();

        return false;
    }

    @Override
    public void create(Title title) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Titles"
                + "(name, author, year, userid)"
                + "VALUES (?,?,?,?)");
        stmt.setString(1, title.getName());
        stmt.setString(2, title.getAuthor());
        stmt.setInt(3, title.getYear());
        stmt.setInt(4, title.getUserId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }

    @Override
    public Title read(Title title ,Integer key) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Title title;
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM Titles WHERE id = ? AND userid = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        int rsId;
        if (!rs.next()) {
            return null;
        } else {
            rsId = rs.getInt("id");
        }
        System.out.println(rsName + " SQL " + rsAuthor + " " + rsYear);
        if (rsName.equals(title.name) && rsAuthor.equals(title.author) && rsYear == year) {
            stmt.close();
            rs.close();
            connection.close();
            return title;
        }

        stmt.close();
        rs.close();
        connection.close();
    }

    @Override
    public Title update(Title object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Title title, Integer id) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");

        PreparedStatement stmt = connection.prepareStatement("DELETE * FROM Titles"
                + "WHERE id = ? AND userid = ?");
        stmt.setInt(1, id);
        stmt.setInt(2, title.getUserId());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    

    @Override
    public List<Title> list(Integer userid) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Title> titleList = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement stmt = connection.prepareStatement("SELECT name, author, year FROM Titles WHERE id = ? AND userid = ?");
        ResultSet rs = stmt.executeQuery("SELECT * FROM Titles");
    }

}
