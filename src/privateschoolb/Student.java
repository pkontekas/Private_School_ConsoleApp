package privateschoolb;
import mySQL.MySQLSelectQuery;
import mySQL.MySQLInsertQuery;
import mySQL.MySQLColumnValue;
import mySQL.MySQLHandler;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private double tuitionFees;
   
    public Student(String firstName, String lastName, String dateOfBirth, double tutionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tutionFees;
    }
    
        public static void createStudents(Scanner scan)
        {
        boolean quit=false;
        String toQuit;
        while (!quit)
            {
            System.out.println("Please enter Name of Student :");
            scan.nextLine();
            String firstName = Validator.getValidAbcString(scan);
            System.out.println("Please enter Surname of Student :");
            String lastName = Validator.getValidAbcString(scan);
            Period allowedAge = Period.of(16, 0, 0);
            System.out.println("Please enter Student's Birthday (age restrictions may apply) :");
            String dateOfBirth = Validator.getValidDateString(scan);
            LocalDate dob = DateHandler.strToLocalDate(dateOfBirth);
            //check for valid age
            if ((DateHandler.comparePeriodsApproximately(DateHandler.findAge(dob), allowedAge ) < 0))
                {
                    System.out.println("Sorry we do not accept Students so young in the Bootcamp!");
                    break;
                }
            System.out.println("Please enter total Tuition Fees in Euros :");
            double tuitionFees = Validator.getValidDouble(5000, scan);
            //use student data to do the insert into the DB
            int studentId = insertStudent(firstName, lastName, dob, tuitionFees);
            if (studentId != 0)
                System.out.println(String.format("A new Student with id %d has been inserted.", studentId));
            else
                System.out.println("Student was not inserted!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue adding Students :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
            }
        }
        
    public static int insertStudent(String firstName, String lastName, LocalDate dateOfBirth, double tuitionFees) 
    {    
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("first_name", firstName));
        valuesToInsert.add(new MySQLColumnValue("last_name", lastName));
	valuesToInsert.add(new MySQLColumnValue("date_of_birth", dateOfBirth));
	valuesToInsert.add(new MySQLColumnValue("tuition_fees", tuitionFees));
	int studentId = MySQLInsertQuery.getInsertedId("students", valuesToInsert);
        return studentId;
    }
    
    public static void enrollStudents(Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        MySQLSelectQuery select = new MySQLSelectQuery(MySQLHandler.getConnected());
        while (!quit)
        {
            //let user select a Student to enroll
            System.out.println("Please select a Student to enroll to a class :");
            int studentId = select.chooseStudentGetId(scan);
            //let user select the Course to put the Student under
            System.out.println("This Student belongs to what Course? Choose one : ");
            int courseId = select.chooseCourseGetId(scan);
            //use student/course data to do the insert into the DB
            int spcId = Course.insertStudentPerCourse(studentId, courseId);
            if (spcId != 0)
                System.out.println(String.format("A new Student with id %d has been enrolled under Course id %d.", studentId, courseId));
            else
                System.out.println("Enrollment failed!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue enrolling Students to Courses :");
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTuitionFees(double tutionFees) {
        this.tuitionFees = tutionFees;
    }

}