package teoskanta.user.dao;

import java.sql.*;
import java.util.*;

public interface UserDao<T, K> {
    
    /**
     * Method for title creation.
     * @param object variable for parameter     
     * @throws SQLException 
     */
    void create(T object) throws SQLException;
    
    /**
     * Method for reading database.
     * @param key variable for a parameter
     * @return returns object T
     * @throws SQLException 
     */
    T read(K key) throws SQLException;
    
    /**
     * Method for updating object in database.
     * @param object variable for parameter
     * @return returns object T
     * @throws SQLException 
     */
    T update(T object) throws SQLException;
    
    /**
     * Method for deleting objects from database.
     * @param object variable for parameter
     * @param key variable for parameter
     * @throws SQLException 
     */
    void delete(T object, K key) throws SQLException;
    
    /**
     * Method for listing objects from database.
     * @param key variable for parameter
     * @return returns List
     * @throws SQLException 
     */
    List<T> list(K key) throws SQLException;

}
