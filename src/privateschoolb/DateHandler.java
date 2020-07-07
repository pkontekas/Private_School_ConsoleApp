package privateschoolb;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 * @author pkontekas
 */
public class DateHandler
{
    public static LocalDate strToLocalDate(String date)
        //format a String date to a Localdate format
    {
        date = DateHandler.fixDateString(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    
    public static LocalDateTime strToLocalDateTime(String dateTime)
        //format a String date to a LocalDateTime format
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }
    
    public static String fixDateString(String d)
        //fixes format to dd/MM/yyyy if it is d/M/yyyy
    {
        String [] tokens = d.split("/");
        if (tokens[0].length() == 1)
            tokens[0] = "0" + tokens[0];
        if (tokens[1].length() == 1)
        {tokens[1] = "0" + tokens[1];}
        return tokens[0]+"/"+tokens[1]+"/"+tokens[2];
    }
    
    public static Period findAge(LocalDate birthdate)
    {   //find period of Age of Person from a Birth Date
        //today date
        LocalDate today = LocalDate.now();
        Period p = Period.between(birthdate, today);
        return p;
    }
    
    public static int comparePeriodsApproximately(Period p1, Period p2) 
    {   //compare two periods and find difference in number of days
        return period2Days(p1) - period2Days(p2);
    }

    private static int period2Days(Period p) 
    {   //change a period to number of days
        if (p == null)
        {return 0;}
        return (p.getYears() * 12 + p.getMonths()) * 30 + p.getDays();
    }

}