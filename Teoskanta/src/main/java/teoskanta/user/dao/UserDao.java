package teoskanta.user.dao;

import java.sql.*;
import java.util.*;

public interface UserDao<T, K> {
    void create(T object) throws SQLException;
    T read(K key) throws SQLException;
    T update(T object) throws SQLException;
    void delete(T object, K key) throws SQLException;
    List<T> list(K key) throws SQLException;
    
}
