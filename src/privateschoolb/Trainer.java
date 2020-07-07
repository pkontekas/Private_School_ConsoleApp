package privateschoolb;
import mySQL.MySQLSelectQuery;
import mySQL.MySQLInsertQuery;
import mySQL.MySQLColumnValue;
import mySQL.MySQLHandler;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class Trainer {
    
    private static String firstName;
    private static String lastName;
    private static String subject;
    
    public Trainer(String firstName, String lastName, String subject) {
        Trainer.firstName = firstName;
        Trainer.lastName = lastName;
        Trainer.subject = subject;
    }
        
    public static void createTrainers(Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        while (!quit)
        {
            System.out.println("Please enter first name of Trainer :");
            //added a scan next line due to java problems with scanning strings in a row
            scan.nextLine();
            firstName = Validator.getValidAbcString(scan);
            System.out.println("Please enter last name of Trainer :");
            lastName = Validator.getValidAbcString(scan);
            System.out.println("Please enter Subject of Trainer :");
            subject = scan.nextLine();
            //use trainer data to do the insert into the DB
            int trainerId = insertTrainer(firstName, lastName, subject);
            if (trainerId != 0)
                System.out.println(String.format("A new Trainer with id %d has been inserted.", trainerId));
            else
                System.out.println("Trainer was not inserted!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue adding Trainers :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
        }
    }
        
    public static int insertTrainer(String firstName, String lastName, String subject)
    {
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("first_name", firstName));
        valuesToInsert.add(new MySQLColumnValue("last_name", lastName));
	valuesToInsert.add(new MySQLColumnValue("subject", subject));
	int trainerId = MySQLInsertQuery.getInsertedId("trainers", valuesToInsert);
        return trainerId;
    }
    
    public static void assignTrainers(Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        MySQLSelectQuery select = new MySQLSelectQuery(MySQLHandler.getConnected());
        while (!quit)
        {
            //let user select a Trainer to assign
            System.out.println("Please select a Trainer to assign to a class :");
            int trainerId = select.chooseTrainerGetId(scan);
            //let user select the Course to put the Trainer under
            System.out.println("This Trainer is assigned to what Course? Choose one : ");
            int courseId = select.chooseCourseGetId(scan);
            //use trainer/course data to do the insert into the DB
            int trainerPerCourseId = Course.insertTrainerPerCourse(courseId, trainerId);
            if (trainerPerCourseId != 0)
                System.out.println(String.format("A new Trainer with id %d has been assigned under Course id %d.", trainerId, courseId));
            else
                System.out.println("Trainer assignment failed!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue assigning Trainers to Courses :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
        }
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        Trainer.firstName = firstName;
    }

    public void setLastName(String lastName) {
        Trainer.lastName = lastName;
    }

}