package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection getPostgresSQLConnection(){
        //Test de connexion à la BDD
        String dbUrl = "jdbc:postgresql://localhost:5432/dbStudent";
        String userName = "postgres";
        String userPassword = "Wazemmeslille59!!!";

        try{
            Connection conn = DriverManager.getConnection(dbUrl,userName,userPassword);
            if(conn != null){
                System.out.println("Connexion OK !");
            }else{
                System.out.println("Connexion ECHOUE !");
            }
            return conn;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
