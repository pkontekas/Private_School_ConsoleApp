package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * @author pkontekas
 */
public class MySQLInsertQuery {
    
    public ArrayList col;
    public String columnFormat;
    
    public static String createInsertSqlStatement(String tableName , ArrayList<MySQLColumnValue> valuesToInsert)
    {
	//method to dynamically create sql statement based on tableName and valuesToInsert
        String statement = "INSERT INTO " + tableName + "(";
	String questionMarks = " VALUES(";
	for (int i = 0; i < valuesToInsert.size(); i++)
	{
            statement += valuesToInsert.get(i).columnName;
            questionMarks += "?";
            if (i != (valuesToInsert.size() - 1))
                {
                    statement += ",";
                    questionMarks += ",";
		}
	}
	statement += ")";
	questionMarks += ")";
	statement += questionMarks;
	return statement;
    }
    
    public static int getInsertedId(String tableName, ArrayList<MySQLColumnValue> valuesToInsert)
    {	//method to dynamically insert into any table, any values to insert and get back the inserted ID
	ResultSet result = null;
        int insertedId = 0;
	//dynamically create the sql statement
	String sql = createInsertSqlStatement(tableName, valuesToInsert);

        try (Connection conn = MySQLHandler.getConnected();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
            {
                // set dynamic parameters for statement
                for (int i = 0; i < valuesToInsert.size(); i++)
                {
                    if (valuesToInsert.get(i).value instanceof String)
                        {
                            statement.setString(i+1, (String) valuesToInsert.get(i).value);
			}
                        else if (valuesToInsert.get(i).value instanceof Integer)
                        {
                            statement.setInt(i+1, (int) valuesToInsert.get(i).value);
                        }
                        else if (valuesToInsert.get(i).value instanceof Double)
                        {
                            statement.setDouble(i+1, (double) valuesToInsert.get(i).value);
                        }
                        else if (valuesToInsert.get(i).value instanceof LocalDateTime)
                        {
                            statement.setObject(i+1, (LocalDateTime) valuesToInsert.get(i).value);
                        }
                        else if (valuesToInsert.get(i).value instanceof LocalDate)
                        {
                            statement.setObject(i+1, (LocalDate) valuesToInsert.get(i).value);
                        }
                        //Object used for other Data Types due to java method constraints
                        else if (valuesToInsert.get(i).value instanceof Object)
                        {
                            statement.setObject(i+1, valuesToInsert.get(i).value);
                        }
		}
                int rowAffected = statement.executeUpdate();
                if (rowAffected == 1)
                    {
                    // get inserted row id
                    result = statement.getGeneratedKeys();
                if(result.next())
                    insertedId = result.getInt(1);
                    }
                if (result != null) {
                    try {
                        result.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        catch (SQLException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Something went wrong!! Duplicate entry most likely!");
            }
        catch (NullPointerException e)
            {
                e.printStackTrace();
                System.out.println("Something went wrong on getInsertedId Method!!");
            }
	return insertedId;
	}
}