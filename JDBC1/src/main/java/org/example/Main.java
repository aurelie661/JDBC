package org.example;
import org.example.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();
            System.out.println("Merci de saisir le prénom : ");
            String firstName = scanner.nextLine();
            System.out.println("Merci de saisir le nom : ");
            String lastName = scanner.nextLine();
            System.out.println("Merci de saisir le numéro de la classe : ");
            int nbClass = scanner.nextInt();
            System.out.println("Merci de saisir la date d'obtention du diplôme : ");
            Date dateOfDiploma = Date.valueOf(scanner.next());

            String sql = "INSERT INTO student (firstName,lastName,nbClass,dateOfDiploma) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,nbClass);
            preparedStatement.setDate(4,dateOfDiploma);
            int nbRow = preparedStatement.executeUpdate();
            System.out.println("Nombre de ligne "+nbRow);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn != null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}