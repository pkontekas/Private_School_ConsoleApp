package privateschoolb;
import mySQL.MySQLSelectQuery;
import mySQL.MySQLInsertQuery;
import mySQL.MySQLColumnValue;
import mySQL.MySQLHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class Assignment {
    
    private int id;
    private int courseId;
    private String title;
    private String description;
    private String subDateTime;
    private double oralMark;
    private double totalMark;

    public Assignment(int id, int courseId, double oralMark, double totalMark) {
        this.id = id;
        this.courseId = courseId;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Assignment(int id, int courseId, String title, String description, String subDateTime, double oralMark, double totalMark) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }
    
        public static void createAssignments(Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        MySQLSelectQuery select = new MySQLSelectQuery(MySQLHandler.getConnected());
        while (!quit)
        {
            System.out.println("Please enter title of Assignment :");
            //added a scan next line due to java problems with scanning strings in a row
            scan.nextLine();
            String title = scan.nextLine();
            System.out.println("Please enter Description of Assignment :");
            String description = scan.nextLine();  
            System.out.println("Please enter Deadline Date of Assignment :");
            String strDate = DateHandler.fixDateString(Validator.getValidDateString(scan));
            String strDateTime = strDate + " 11:59:59";
            LocalDateTime subDateTime = DateHandler.strToLocalDateTime(strDateTime);
            System.out.println("Please enter Max Oral Mark of Assignment (maximum of 50) :");
            double oralMark = Validator.getValidDouble(50, scan);
            System.out.println("Please enter Max Total Mark of Assignment (maximum of 100) :");
            double totalMark = Validator.getValidDouble(100, scan);
            //let user select the Course to put the new assignment under
            System.out.println("This assignment belongs to what Course? Choose one : ");
            int courseId = select.chooseCourseGetId(scan);
            //use assignment data to do the insert into the DB
            int assignmentId = insertAssignment(courseId, title, description, subDateTime, oralMark, totalMark);
            if (assignmentId != 0)
                System.out.println(String.format("A new Assignment with id %d has been inserted under Course id %d.", assignmentId, courseId));
            else
                System.out.println("Assignment was not inserted!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue adding Assignments :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
        }
    }
        
    public static int insertAssignment(int courseId, String title, String description, LocalDateTime subDateTime, double maxOralMark, double maxTotalMark) 
    {
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("course_id", courseId));
        valuesToInsert.add(new MySQLColumnValue("title", title));
        valuesToInsert.add(new MySQLColumnValue("description", description));
	valuesToInsert.add(new MySQLColumnValue("sub_date_time", subDateTime));
	valuesToInsert.add(new MySQLColumnValue("oral_mark", maxOralMark));
        valuesToInsert.add(new MySQLColumnValue("total_mark", maxTotalMark));
	int assignmentId = MySQLInsertQuery.getInsertedId("assignments", valuesToInsert);
        return assignmentId;
    }
    
    public static int insertGradePerStudentAssignment(int studentId, int assignmentId, double stOralMark, double stTotalMark)
    {    
        ArrayList<MySQLColumnValue> valuesToInsert = new ArrayList<>();
        valuesToInsert.add(new MySQLColumnValue("student_id", studentId));
        valuesToInsert.add(new MySQLColumnValue("assignment_id", assignmentId));
        valuesToInsert.add(new MySQLColumnValue("st_oralmark", stOralMark));
        valuesToInsert.add(new MySQLColumnValue("st_totalmark", stTotalMark));
	int stAssGradeId = MySQLInsertQuery.getInsertedId("student_grades", valuesToInsert);
        return stAssGradeId;
    }
    
    public static void gradeStudentAssignments(ArrayList<Assignment> assignmentGrades, Scanner scan)
    {
        boolean quit=false;
        String toQuit;
        MySQLSelectQuery select = new MySQLSelectQuery(MySQLHandler.getConnected());
        while (!quit)
        {
            //let user select a Student to grade
            System.out.println("Please select a Student to grade :");
            int studentId = select.chooseStudentGetId(scan);
            //let user select the Assignment to grade for the Student only from non-graded ones
            System.out.println("Choose one of the Student's pending assignments to grade : ");
            int assignmentId = select.chooseAssignmentToGradeGetId(scan, studentId);
            //find the max oral marks/total marks of the assignment so that the student will use correct range of marks
            double maxOralMark = 0;
            double maxTotalMark = 0;
            for (Assignment a: assignmentGrades)
            {
                if (a.getId() == assignmentId)
                {
                    maxOralMark = a.getOralMark();
                    maxTotalMark = a.getTotalMark();
                }
            }
            System.out.println("Please enter Oral Mark of Assignment (limited by assignment maximum) :");
            double stOralMark = Validator.getValidDouble(maxOralMark, scan);
            System.out.println("Please enter Total Mark of Assignment (limited by assignment maximum) :");
            double stTotalMark = Validator.getValidDouble(maxTotalMark, scan);
            //use student/assignment data to do the insert into the DB
            int gradeId = Assignment.insertGradePerStudentAssignment(studentId, assignmentId, stOralMark, stTotalMark);
            if (gradeId != 0)
                System.out.println(String.format("An assignment with id %d has been graded under Student id %d.", assignmentId, studentId));
            else
                System.out.println("Something went wrong! Grading failed!");
            System.out.println("Press 'Q' to quit or any key and ENTER to continue grading Student Assignments :");
            toQuit = scan.next();
            if (toQuit.equalsIgnoreCase("Q"))
                quit = true;
        }
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSubDateTime() {
        return subDateTime;
    }

    public double getOralMark() {
        return oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubDateTime(String subDateTime) {
        this.subDateTime = subDateTime;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }
    
    
}