package privateschoolb;
import mySQL.MySQLInsertQuery;
import mySQL.MySQLColumnValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class Course {
    private String title;
    private String stream;
    private String type;
    private String startDate;
    private String endDate;

    public Course(String title, String stream, String type, String startDate, String endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public static void createCourses(Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        while (!quit)
        {
            System.out.println("Please enter Title of Course : ");
            //empty the scanner - to avoid java issue
            scan.nextLine();
            String title = scan.nextLine();
            System.out.println("Please enter Stream of Course (for example Java) :");
            String stream = scan.nextLine();
            System.out.println("Please select Type of Course :\n1. Part-time.\n2. Full-time.");
            int typeChoice = Validator.getValidMenuChoice(2, scan);
            String type;
            if (typeChoice==1)
                {type = "Part-time";}
            else
                {type = "Full-time";}
            System.out.println("Please enter Start Date of Course :");
            scan.nextLine();
            String strStartDate = Validator.getValidDateString(scan);
            LocalDate startDate = DateHandler.strToLocalDate(strStartDate);
            //Make sure the End Date of the Course is after the Start Date of the Course
            String strEndDate;
            LocalDate endDate;
            boolean isPositiveCourseDuration;
            do{
            System.out.println("Please enter End Date of Course (must be after Start Date) :");
            strEndDate = Validator.getValidDateString(scan);
            endDate = DateHandler.strToLocalDate(strEndDate);
            isPositiveCourseDuration = endDate.isAfter(startDate);
            }
            while (isPositiveCourseDuration == false);
            //use course data to do the insert into the DB
            int courseId = insertCourse(title, stream, type, startDate, endDate);
            if (courseId != 0)
                System.out.println(String.format("A new Course with id %d has been inserted.", courseId));
            else
                System.out.println("Course was not inserted!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue adding Courses :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
        }
    }
    
    public static int insertCourse(String title, String stream, String type, LocalDate startDate, LocalDate endDate) 
    {    
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("title", title));
        valuesToInsert.add(new MySQLColumnValue("stream", stream));
	valuesToInsert.add(new MySQLColumnValue("type", type));
	valuesToInsert.add(new MySQLColumnValue("start_date", startDate));
        valuesToInsert.add(new MySQLColumnValue("end_date", endDate));
	int courseId = MySQLInsertQuery.getInsertedId("courses", valuesToInsert);
        return courseId;
    }
        
    public static int insertStudentPerCourse(int studentId, int courseId)
    {    
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("student_id", studentId));
        valuesToInsert.add(new MySQLColumnValue("course_id", courseId));
	int studentPerCourseId = MySQLInsertQuery.getInsertedId("student_has_courses", valuesToInsert);
        return studentPerCourseId;
    }
    
    public static int insertTrainerPerCourse(int courseId, int trainerId)
    {    
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("course_id", courseId));
        valuesToInsert.add(new MySQLColumnValue("trainer_id", trainerId));
	int trainerPerCourseId = MySQLInsertQuery.getInsertedId("trainer_has_courses", valuesToInsert);
        return trainerPerCourseId;
    }
    
    public String getTitle() {
        return title;
    }

    public String getStream() {
        return stream;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}