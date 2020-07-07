package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import privateschoolb.Assignment;
import privateschoolb.Validator;
/**
 * @author pkontekas
 */
public class MySQLSelectQuery {
    
    private Connection conn;
    public ArrayList col;
    public String columnFormat;

    public MySQLSelectQuery(Connection connection){
        conn = connection;
    }

    public void allStudents()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT * FROM students"; 
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Students are shown below : ");
            //get Column Labels in an Arraylist
            col = MySQLHandler.getColumnList(result);
            //format Column labels for Alignment
            columnFormat = "%4s%25s%25s%20s%20s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4));
            System.out.println();
            while(result.next())
                {
                    //format data from Result Set for Alignment
                    System.out.format(columnFormat, result.getInt(1), result.getString(2), result.getString(3), result.getDate(4), result.getDouble(5));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
             e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void allTrainers()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT * FROM trainers ORDER BY trainers.id";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Trainers are shown below : ");
            //get Column Labels in an Arraylist
            col = MySQLHandler.getColumnList(result);
            //format Column labels for Alignment
            columnFormat = "%4s%25s%25s%35s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3));
            System.out.println();
            while(result.next())
                {
                    //format data from Result Set for Alignment
                    System.out.format(columnFormat, result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void allCourses()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT * FROM courses";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Courses are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%4s%30s%20s%20s%15s%15s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getDate(6));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void allAssignments()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT * FROM assignments";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Assignments are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%3s%10s%30s%35s%25s%15s%15s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5), col.get(6));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getInt(1), result.getInt(2), result.getString(3), result.getString(4), result.getTimestamp(5), result.getDouble(6), result.getDouble(7));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void studentsPerCourse()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT s.first_name, s.last_name, s.date_of_birth, c.title, c.stream, c.type\n" +
            "FROM STUDENTS s, COURSES c, student_has_courses sc\n" +
            "WHERE sc.student_ID = s.id AND sc.course_ID = c.id ORDER BY c.id;";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("Students per Course are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%20s%25s%16s%30s%16s%16s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getString(1), result.getString(2), result.getDate(3), result.getString(4), result.getString(5), result.getString(6));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void trainersPerCourse()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT t.first_name, t.last_name, t.subject, c.title, c.stream, c.type\n" +
            "FROM courses c, trainers t, trainer_has_courses tc WHERE c.id = tc.course_id AND t.id = tc.trainer_id ORDER BY c.id;";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("Trainers per Course are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%20s%25s%25s%25s%16s%16s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void assignmentsPerCourse()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT a.title, a.description, a.sub_date_time as deadline, c.title, c.stream, c.type\n" +
            "FROM assignments a, courses c WHERE c.id = a.course_ID ORDER BY c.id;";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("Assignments per Course are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%30s%30s%25s%15s%15s%15s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getString(1), result.getString(2), result.getTimestamp(3), result.getString(4), result.getString(5), result.getString(6));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void assignmentGradesPerStudent()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT s.id as sid, s.first_name, s.last_name, courses.title as course,\n" +
            "assignments.title as assignment, student_grades.st_oralmark, student_grades.st_totalmark\n" +
            "FROM students s\n" +
            "JOIN student_has_courses ON student_has_courses.student_id = s.id\n" +
            "JOIN courses ON courses.id = student_has_courses.course_id\n" +
            "JOIN assignments ON assignments.course_id = courses.id\n" +
            "JOIN student_grades ON student_grades.assignment_id = assignments.id AND student_grades.student_id = s.id\n" +
            "UNION ALL\n" +
            "SELECT s.id as sid, s.first_name, s.last_name, courses.title as course,\n" +
            "a.title as assignment, 'N/A', 'N/A'\n" +
            "FROM students s\n" +
            "JOIN student_has_courses ON student_has_courses.student_id = s.id\n" +
            "JOIN courses ON courses.id = student_has_courses.course_id\n" +
            "JOIN assignments a ON a.course_id = courses.id\n" +
            "WHERE NOT EXISTS (SELECT 1 FROM student_grades sa WHERE sa.student_id = s.id AND sa.assignment_id = a.id)\n" +
            "ORDER BY sid;"; 
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All Assignments per Student are shown below with appropriate grades where applicable : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%3s%20s%25s%20s%25s%15s%15s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3), col.get(4), col.get(5), col.get(6));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getObject(6), result.getObject(7));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void studentManyCourses()
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT s.id as student_id, s.first_name, s.last_name, COUNT(*) as course_count\n" +
             "FROM students s, student_has_courses sc\n" +
             "WHERE sc.student_ID=s.id GROUP BY s.id\n" +
             "HAVING COUNT(s.id)>1 ORDER BY s.id;";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("The following Students belong to many Courses : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%10s%30s%30s%15s";
            System.out.format(columnFormat, col.get(0), col.get(1), col.get(2), col.get(3));
            System.out.println();
            while(result.next())
                {
                    System.out.format(columnFormat, result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                    System.out.println();
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public int chooseCourseGetId(Scanner scan)
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int courseId = 0;
        try {
            conn = MySQLHandler.getConnected();
            ArrayList<Integer> idCollection = new ArrayList<>();
            String query = "SELECT * FROM courses";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Courses are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%10s%30s%20s%20s%15s%15s";
            System.out.format(columnFormat, "course_no", col.get(1), col.get(2), col.get(3), col.get(4), col.get(5));
            System.out.println();
            int courseCount = 0;
            while(result.next())
                {
                    courseCount +=1;
                    idCollection.add(result.getInt(1));
                    System.out.format(columnFormat, courseCount + ". ", result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getDate(6));
                    System.out.println();
                }
            int courseChoice = Validator.getValidMenuChoice(courseCount, scan);
            courseId = idCollection.get(courseChoice-1);
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courseId;
    }
    
    public int chooseStudentGetId(Scanner scan)
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int studentId = 0;
        try {
            conn = MySQLHandler.getConnected();
            ArrayList<Integer> idCollection = new ArrayList<>();
            String query = "SELECT * FROM students";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Students are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%12s%25s%25s%20s%15s";
            System.out.format(columnFormat, "student_no", col.get(1), col.get(2), col.get(3), col.get(4));
            System.out.println();
            int studentCount = 0;
            while(result.next())
                {
                    studentCount +=1;
                    idCollection.add(result.getInt(1));
                    System.out.format(columnFormat, studentCount + ". ", result.getString(2), result.getString(3), result.getDate(4), result.getDouble(5));
                    System.out.println();
                }
            int studentChoice = Validator.getValidMenuChoice(studentCount, scan);
            studentId = idCollection.get(studentChoice-1);
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentId;
    }
    
    public int chooseTrainerGetId(Scanner scan)
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int trainerId = 0;
        try {
            conn = MySQLHandler.getConnected();
            ArrayList<Integer> idCollection = new ArrayList<>();
            String query = "SELECT * FROM trainers ORDER by trainers.id";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("All the Trainers are shown below : ");
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%12s%25s%25s%30s";
            System.out.format(columnFormat, "trainer_no", col.get(1), col.get(2), col.get(3));
            System.out.println();
            int trainerCount = 0;
            while(result.next())
                {
                    trainerCount +=1;
                    idCollection.add(result.getInt(1));
                    System.out.format(columnFormat, trainerCount + ". ", result.getString(2), result.getString(3), result.getString(4));
                    System.out.println();
                }
            int trainerChoice = Validator.getValidMenuChoice(trainerCount, scan);
            trainerId = idCollection.get(trainerChoice-1);
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return trainerId;
    }

    public int chooseAssignmentToGradeGetId(Scanner scan, int studentId)
    {
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int assignmentId = 0;
        try {
            conn = MySQLHandler.getConnected();
            ArrayList<Integer> idCollection = new ArrayList<>();
            String query = "SELECT a.id, s.first_name, s.last_name, courses.title as course,\n" +
            "a.title as assignment, 'N/A' as oral_mark, 'N/A' as total_mark\n" +
            "FROM students s\n" +
            "JOIN student_has_courses ON student_has_courses.student_id = s.id\n" +
            "JOIN courses ON courses.id = student_has_courses.course_id\n" +
            "JOIN assignments a ON a.course_id = courses.id\n" +
            "WHERE NOT EXISTS (SELECT 1 FROM student_grades sa WHERE sa.student_id = s.id AND sa.assignment_id = a.id)\n" +
            "AND s.id = " + studentId + ";";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            col = MySQLHandler.getColumnList(result);
            columnFormat = "%12s%25s%25s%20s%30s%15S%15s";
            System.out.format(columnFormat, "assign_no", col.get(1), col.get(2), col.get(3), col.get(4), col.get(5), col.get(6));
            System.out.println();
            int assignmentCount = 0;
            while(result.next())
                {
                    assignmentCount +=1;
                    idCollection.add(result.getInt(1));
                    System.out.format(columnFormat, assignmentCount + ". ", result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getObject(6), result.getObject(7));
                    System.out.println();
                }
            int assignmentChoice = Validator.getValidMenuChoice(assignmentCount, scan);
            assignmentId = idCollection.get(assignmentChoice-1);
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignmentId;
    }
    
    public ArrayList<Assignment> getStudentFutureMaxMarks(Scanner scan) 
    {   
        conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Assignment> assignmentGrades = new ArrayList<>();
        try {
            conn = MySQLHandler.getConnected();
            String query = "SELECT * FROM assignments";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery(); 
            while(result.next())
                {
                    Assignment assignment = new Assignment(0, 0, 0, 0);
                    assignment.setId(result.getInt(1));
                    assignment.setCourseId(result.getInt(2));
                    assignment.setOralMark(result.getDouble(6));
                    assignment.setTotalMark(result.getDouble(7));
                    assignmentGrades.add(assignment);
                }
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally 
        {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignmentGrades;
    }   
}