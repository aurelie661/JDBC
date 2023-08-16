package org.example;

import org.example.utils.DatabaseManager;

import java.sql.*;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try{
            conn = DatabaseManager.getPostgresSQLConnection();
            /*System.out.println("Merci de saisir le prénom : ");
            String firstName = scanner.nextLine();
            System.out.println("Merci de saisir le nom : ");
            String lastName = scanner.nextLine();*/

            /*//execution de requete sans retour
            String sql = "INSERT INTO person (firstName,lastName) VALUES ('"+firstName+"','"+lastName+"')";
            //avec une requete preparee
            String sql = "INSERT INTO person (firstName,lastName) VALUES (?,?)";*/

           /* //Facon 1 => execution de requete sans retour
            Statement statement = conn.createStatement();
            boolean res = statement.execute(sql);
            if(res){
                System.out.println("Des données envoyées par la requête.");
            }else{
                System.out.println("Aucune données envoyées par la requête.");
            }*/

          /*  //Facon 2 => avec une requete preparee
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            //preparedStatement.execute(); retour boolean
            int nbRow = preparedStatement.executeUpdate();
            System.out.println("Nombre de ligne "+nbRow);

            //Version avec récuperation de l'Id générer
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            int nbRow = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println("Nombre de ligne "+nbRow);
            if(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }*/

            //FACON 3 => avec une requete de lecture
            String sql = "SELECT * FROM person";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+") "+
                                    resultSet.getString("firstName")+" "+
                                    resultSet.getString("lastName"));
            }

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
