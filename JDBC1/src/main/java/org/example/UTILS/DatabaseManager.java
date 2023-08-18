package org.example.UTILS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection getPostgresSQLConnection(){

        String dbUrl = "jdbc:postgresql://localhost:5432/dbStudent";
        String userName = "postgres";
        String userPassword = "Wazemmeslille59!!!";

        try{
            Connection conn = DriverManager.getConnection(dbUrl,userName,userPassword);
            if(conn != null){
                System.out.println("Connexion OK !\n");
            }else{
                System.out.println("Connexion ECHOUE !\n");
            }
            return conn;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
