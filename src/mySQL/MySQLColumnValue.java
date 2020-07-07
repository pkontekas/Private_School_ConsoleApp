package mySQL;
/**
 * @author pkontekas
 */
public class MySQLColumnValue {
    
    String columnName;
    Object value;
    public MySQLColumnValue(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
}