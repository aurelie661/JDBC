package org.example.class_;
import org.example.utils.DatabaseManager;

import java.sql.*;

public class Student {
    private final long id;
    private String firstName;
    private String lastName;
    private int nbClass;
    private Date dateOfDiploma;

    public Student(String firstName, String lastName, int nbClass, Date dateOfDiploma) {
        this.id = getId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nbClass = nbClass;
        this.dateOfDiploma = dateOfDiploma;
    }
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNbClass() {
        return nbClass;
    }

    public void setNbClass(int nbClass) {
        this.nbClass = nbClass;
    }

    public Date getDateOfDiploma() {
        return dateOfDiploma;
    }

    public void setDateOfDiploma(Date dateOfDiploma) {
        this.dateOfDiploma = dateOfDiploma;
    }

    public static Student Save(String firstName,String lastName,int nbClass,Date dateOfDiploma){
        Student student = new Student(firstName,lastName,nbClass,dateOfDiploma);//todo à finir
        Connection conn = null;

        try {
            conn = DatabaseManager.getPostgresSQLConnection();

            String sql = "INSERT INTO student (firstName,lastName,nbClass,dateOfDiploma) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,nbClass);
            preparedStatement.setDate(4,dateOfDiploma);

            int nbRow = preparedStatement.executeUpdate();
            System.out.println("Nombre de ligne "+nbRow);
            if(nbRow > 0){

                System.out.println("L'étudiant a été ajouter avec succés .");
            }else{
                System.out.println("Aucun étudiant n'a pas été ajouter");
            }
            return student;

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
        return null;
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
            int nbRow = preparedStatement.executeUpdate();
            Student.getAll();
            if(nbRow > 0){
                System.out.println("L'étudiant a été supprimer avec succés");
            }else{
                System.out.println("Erreur");
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

