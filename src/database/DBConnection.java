/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DBConnection {

    private static DBConnection dbconnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root", "root");
    }
    
    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dbconnection == null) {
            dbconnection = new DBConnection();
        }
        return dbconnection;
    }
}
