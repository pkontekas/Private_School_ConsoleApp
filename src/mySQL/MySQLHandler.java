package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @author pkontekas
 */
public class MySQLHandler {
    
    private static final String USERNAME = "java_user";
    private static final String PASSWORD = "java_1234!";
    private static final String CONN =  "jdbc:mysql://localhost:3306/privateschool";
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    
    public static Connection getConnected() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
            return connection;
            }
        catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
                System.out.println("Something went wrong!");
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
                if (connection == null)
                {
                    System.out.println("MySQL not connected!\nCheck for DB connectivity before proceeding!");
                    return null;
                }
            }
        return null;
    }
    
    public static String getColumnNames(ResultSet result) {
        //use a result set as input, to find and return all the required column names as String with spaces between
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            String columnName = "";
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    columnName += rsmd.getColumnLabel(i) + " ";
                }
            return columnName;
            }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        return null;
    }
    
    public static ArrayList<String> getColumnList(ResultSet result) {
            //use a result set as input, to find and return all the required column names as String with spaces between
            ArrayList<String> columnList = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    columnList.add(rsmd.getColumnLabel(i));
                }
            return columnList;
                }
        catch (SQLException e)
            {
                e.printStackTrace();
            }
        return null;
    }
    
}