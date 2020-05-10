package teoskanta.title.dao;

import java.sql.*;
import java.util.*;

public interface TitleDao<T, K> {
    /**
     * Method for title creation.
     * @param object variable for parameter
     * @param key variable for parameter
     * @throws SQLException 
     */
    void create(T object, K key) throws SQLException;
    
    /**
     * Method for reading database.
     * @param object variable for parameter
     * @param key variable for parameter
     * @return object T
     * @throws SQLException 
     */
    T read(T object, K key) throws SQLException;
    
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
