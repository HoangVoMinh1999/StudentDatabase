/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;

/**
 *
 * @author vomin
 */
import java.sql.*;
import java.util.*;

public class StudentDatabase {

    /**
     * @param args the command line arguments
     */
    public static Driver driver = new org.postgresql.Driver();
    public static String conString = "jdbc:postgresql://localhost:5432/VMH";
    public static Properties info = new Properties();

    public static void main(String[] args) {
        // TODO code application logic here
        //--- Check driver postgre ---//
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //--- Connect to database ---//
        //--- infomation of database ---//
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", "postgres");
        info.setProperty("password", "1104");
        try {
            //--- connect to database ---//
            Connection connection = DriverManager.getConnection(conString, info);
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "student", null);
            if (resultSet.next()) {
                System.out.println(resultSet);
            } else {
                create_database();
            }
            //--- process of database ---/
            Statement sm = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //--- Method to connect database
    public static Connection create_connection() throws SQLException {
        return DriverManager.getConnection(conString, info);
    }
    //--- Method to create database if it is not exist
    public static void create_database(){
        try{
            Connection connection=create_connection();
            connection.getAutoCommit();
            Statement stmt = connection.createStatement();
            //--- Create new table ---//
            String sql = "CREATE TABLE STUDENT " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " SCORE          FLOAT     NOT NULL, " +
            " ADDRESS        CHAR(50))";
            stmt.executeUpdate(sql);
            //--- Insert data ---//
            sql = "INSERT INTO STUDENT (ID,NAME,SCORE,ADDRESS) "+ "VALUES (1, 'Paul', 10, 'California');";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO STUDENT (ID,NAME,SCORE,ADDRESS) "+ "VALUES (2, 'Hoang', 10, 'HCMC');";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO STUDENT (ID,NAME,SCORE,ADDRESS) "+ "VALUES (3, 'Tran', 9, 'QN');";
            stmt.executeUpdate(sql);
            
            stmt.close();
          
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
