package org.example.class_;
import org.example.utils.DatabaseManager;
import java.sql.*;
import java.util.Date;

public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int nbClass;
    private Date dateOfDiploma;

    public Student(String firstName, String lastName, int nbClass, Date dateOfDiploma) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nbClass = nbClass;
        this.dateOfDiploma = dateOfDiploma;
    }

    public Student(long id, String firstName, String lastName, int nbClass, Date dateOfDiploma) {
        this(firstName,lastName,nbClass,dateOfDiploma);
        this.id = id;
    }
    public static void Save(String firstName, String lastName, int nbClass, Date dateOfDiploma){
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();

            String sql = "INSERT INTO student (firstName,lastName,nbClass,dateOfDiploma) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,nbClass);
            preparedStatement.setDate(4, (java.sql.Date) dateOfDiploma);

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

    public static void getAll(){
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
                                resultSet.getString("lastName") + " Classe n°: " +
                                resultSet.getInt("nbClass") + " Date d'obtention du diplôme " +
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
    public static void getAllByClass(int nbClass){
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();

            String sql = "SELECT * FROM student WHERE nbClass = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,nbClass);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + ") " +
                                resultSet.getString("firstName") + " " +
                                resultSet.getString("lastName") + " Classe n°: " +
                                resultSet.getInt("nbClass") + " Date d'obtention du diplôme " +
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
    public static void DeleteStudentById(long id){
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();
            String sql = "DELETE FROM student WHERE id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            Student.getAll();

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
    @Override
    public String toString() {
        return "Student :" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nbClass=" + nbClass +
                ", dateOfDiploma=" + dateOfDiploma +
                '.';
    }
}

