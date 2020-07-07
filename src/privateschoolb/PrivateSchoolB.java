package privateschoolb;

import mySQL.MySQLSelectQuery;
import mySQL.MySQLHandler;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class PrivateSchoolB {

    private final Scanner scan;
    private static Connection conn;
    public ArrayList<Assignment> assignmentGrades;
    
    public static void main(String[] args)
    {   // Private School Assignment 1 Part B - pkontekas
        PrivateSchoolB school = new PrivateSchoolB();
        conn = MySQLHandler.getConnected();
        school.welcomeMenu();
    }

    public PrivateSchoolB()
    {
        this.scan = new Scanner(System.in);
    }
    
    public void welcomeMenu()
    {
        System.out.println("Welcome to our Private Coding Bootcamp School in Peoplecert");
        System.out.println("Please proceed with one of the following choices: ");
        System.out.println("1. Report Menu.\n2. Add new School Data.");
        int choice = Validator.getValidMenuChoice(2, scan);
        if (choice==2)
            schoolCreation();
        else
            reportMenu();
    }
    
    public void schoolCreation()
    {
        int choice;
        do
        {
            System.out.println("Choose one of the following options : \n1. Create new Students. \n2. Create new Trainers.");
            System.out.println("3. Create new Courses. \n4. Create new Assignments. \n5. Enroll Students to Courses."
            + "\n6. Assign Trainers to Courses. \n7. Grade Student Assignments. \n8. Enter Reporting Menu.");
            choice = Validator.getValidMenuChoice(8, scan);
            switch (choice)
            {
                case 1:
                        Student.createStudents(scan);
                        break;
                case 2:
                        Trainer.createTrainers(scan);
                        break;
                case 3:
                        Course.createCourses(scan);
                        break;
                case 4:
                        Assignment.createAssignments(scan);
                        break;
                case 5:
                        Student.enrollStudents(scan);
                        break;
                case 6:
                        Trainer.assignTrainers(scan);
                        break;
                case 7:
                        MySQLSelectQuery select = new MySQLSelectQuery(conn);
                        assignmentGrades = select.getStudentFutureMaxMarks(scan);
                        Assignment.gradeStudentAssignments(assignmentGrades, scan);
                        break;
                case 8:
                        System.out.println("Thanks for your input. Here are your available Reports: ");
                        reportMenu();
                        break;
            }
        }
            while (choice!=8);
    }
    
    public void reportMenu()
    {
    int choice;
    MySQLSelectQuery select = new MySQLSelectQuery(conn);
        do
        {
            System.out.println("Choose one of the following options : \n1. Show all Students. \n2. Show all Trainers.\n3. Show all Assignments.");
            System.out.println("4. Show all Courses.\n5. Show Students per Course.\n6. Show Trainers per Course.\n7. Show Assignments per Course.");
            System.out.println("8. Show Assignments per Student.\n9. Show Students that belong to more than one Course.\n10. Exit Program.");
            choice = Validator.getValidMenuChoice(10, scan);
            switch (choice)
            {
                case 1:
                        select.allStudents();
                        break;
                case 2:
                        select.allTrainers();
                        break;
                case 3:
                        select.allAssignments();
                        break;
                case 4:
                        select.allCourses();
                        break;
                case 5:
                        select.studentsPerCourse();
                        break;
                case 6:
                        select.trainersPerCourse();
                        break;
                case 7:
                        select.assignmentsPerCourse();
                        break;
                case 8:
                        //show assignments per student (through their relevant courses)
                        select.assignmentGradesPerStudent();
                        break;
                case 9:
                        select.studentManyCourses();
                        break;
                case 10:
                        System.out.println("Thank you, have a nice Day!!");
                        break;
            }
        }
            while (choice!=10);
    }

}