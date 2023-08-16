package org.example.class_;
import org.example.utils.DatabaseManager;
import java.sql.*;
import java.util.Scanner;

public class IhmConsole {
    private static final Scanner scanner = new Scanner(System.in);
    public static void StartIhm(){
        ShowMenu();
    }
    public static void ShowMenu(){
        int choice;
        do {
            System.out.println("================ Menu Principal ================");
            System.out.println("\t\t1. Ajouter un étudiant");
            System.out.println("\t\t2. Afficher la liste d'étudiant");
            System.out.println("\t\t3. Afficher les étudiants d'une classe");
            System.out.println("\t\t4. Supprimer un étudiant");
            System.out.println("\t\t0. Quitter");
            System.out.print("\t\tVotre choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch(choice){
                case 1 -> AddStudent();
                case 2 -> ShowStudents();
                case 3 -> ShowStudentsInAClass();
                case 4 -> DeleteStudent();
                case 0 -> System.out.println("Merci, Aurevoir!");
                default -> System.out.println("Saisie invalible !");
            }
        }while (choice != 0);
    }

    public static void AddStudent(){
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

    public static void ShowStudents() {
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();
            String sql = "SELECT * FROM student";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + ") " +
                        resultSet.getString("firstName") + " " +
                        resultSet.getString("lastName") + " " +
                        resultSet.getInt("nbClass") + " " +
                        resultSet.getDate("dateOfDiploma") + " \n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void ShowStudentsInAClass(){
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();
            System.out.println("Merci de saisir le numéro de la classe : ");
            int nbClass = scanner.nextInt();

            String sql = "SELECT * FROM student WHERE nbClass = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,nbClass);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                                resultSet.getInt("id") + ") " +
                                resultSet.getString("firstName") + " " +
                                resultSet.getString("lastName") + " " +
                                resultSet.getInt("nbClass") + " " +
                                resultSet.getDate("dateOfDiploma") + " \n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DeleteStudent(){
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();
            ShowStudents();
            System.out.println("Merci de saisir le prénom : ");
            String firstName = scanner.nextLine();
            System.out.println("Merci de saisir le nom : ");
            String lastName = scanner.nextLine();

            String sql = "DELETE FROM student WHERE firstName = ? AND lastName = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.executeUpdate();
            ShowStudents();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
