/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dougl
 */
public class DatabaseConection extends Database{
    
    public Connection connectToDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_BASE_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException error) {
            // Lida com a exceção adequadamente (pode relançar ou registrar o erro)
            throw error;
        }
        return conn;
    }
    
    
}
