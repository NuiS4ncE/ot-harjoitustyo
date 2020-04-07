package teoskanta.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import teoskanta.user.dao.DBUserDao;
import teoskanta.domain.User;
import teoskanta.title.dao.DBTitleDao;

public class TitleService {
    private DBUserDao userDao;
    private User user;
    private DBTitleDao dbTitleDao;
    
    public boolean newTitle(String name, String author, int year){
         System.out.println(name + " " + author + " " + year);
        try{
            dbTitleDao.create(new Title(name, author, year, userDao.getUserIdFromDatabase(user.getUsername(), user.getPassword())));
            if(dbTitleDao.findTitle(name, author, year)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            System.out.println("Title creation had problems: " + e);
        }
        return false;
    }
    
    public boolean deleteTitle(String name, String author, int year, int userid){
         System.out.println(name + " " + author + " " + year);
         
        try{
            dbTitleDao.findTitle(name, author, year, userid);
            //dbTitleDao.delete(name, author, year, userid);
            if(!dbTitleDao.findTitle(name, author, year, userid)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            System.out.println("Title creation had problems: " + e);
        }
        return false;
    }
    
}
