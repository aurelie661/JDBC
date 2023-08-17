package org.example.class_;
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
                case 2 -> Student.getAll();
                case 3 -> ShowStudentsInAClass();
                case 4 -> DeleteStudent();
                case 0 -> System.out.println("Merci, Aurevoir!");
                default -> System.out.println("Saisie invalible !");
            }
        }while (choice != 0);
    }

    public static void AddStudent(){
        System.out.println("Merci de saisir le prénom : ");
        String firstName = scanner.nextLine();
        System.out.println("Merci de saisir le nom : ");
        String lastName = scanner.nextLine();
        System.out.println("Merci de saisir le numéro de la classe : ");
        int nbClass = scanner.nextInt();
        System.out.println("Merci de saisir la date d'obtention du diplôme : ");
        Date dateOfDiploma = Date.valueOf(scanner.next());
        Student.Save(firstName, lastName, nbClass, dateOfDiploma);
        //System.out.println("L'étudiant a bien été ajouté à la position :");

    }
    public static void ShowStudentsInAClass(){
        System.out.println("Merci de saisir le numéro de la classe : ");
        int nbClass = scanner.nextInt();
        Student.getAllByClass(nbClass);
    }

    public static void DeleteStudent() {
        Student.getAll();
        System.out.println("Merci de saisir l'identifiant de l'étudiant : ");
        long id = scanner.nextLong();
        Student.DeleteStudentById(id);
        System.out.println();
        Student.getAll();
    }
}
