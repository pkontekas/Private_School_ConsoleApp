package privateschoolb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class Validator {
    
    public static int getValidMenuChoice(int numChoices, Scanner scan) {
        //validation for number of Choices up to numChoices, for Menus and not only
        int number;
        do {
            System.out.print("Please enter a valid choice: ");
            while (!scan.hasNextInt()) {
                String input = scan.next();
                System.out.println(input + " is not a valid choice. You will have to retry.");
            }
            number = scan.nextInt();
        } while (number<1 || number>numChoices);
        return number;
    }
    
    public static double getValidDouble(double maxNumber, Scanner scan) {
        //validation for numbers from 0 up to maxNumber, for Double number checks
        double number;
        do {
            System.out.print("Please enter a valid choice: ");
            while (!scan.hasNextDouble()) {
                String input = scan.next();
                System.out.println(input + " is not a valid choice. You will have to retry.");
            }
            number = scan.nextDouble();
        } while (number<0 || number>maxNumber);
        return number;
    } 
    
    private static boolean validateJavaDate(String strDate)
   {
        // validation for Date formatting in dd/MM/YYYY
	// Check if date is 'null'
	if (strDate.trim().equals(""))
	    return false;
	// Date is not 'null'
	else
	{
	    //Set preferred date format, for example dd/MM/yyyy
	    SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
	    sdformat.setLenient(false);
	    // Create Date object, parse the string into date
	    try
	    {
	        sdformat.parse(strDate);
	    }
	    // Date format is invalid
	    catch (ParseException e)
	    {
	        System.out.println(strDate+" is Invalid Date formatting, please try again!");
	        return false;
	    }
	    // Return true if date format is valid
	    return true;
	}
   }

    public static String getValidDateString(Scanner scan)
        //validate choice to return the correct date string else give input again
    {
        boolean dateValidated;
        String dateString;
		do {
			System.out.print("Enter a valid date of dd/MM/yyyy format: ");
			dateString = scan.nextLine();
			dateValidated = validateJavaDate(dateString);
                   }
                while (dateValidated == false);
			return dateString;
    }

    private static boolean validateAbcString(String str)
        //validate if String is alphabetic or not
    {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z'))
                return false;
        }
        return true;
   }

    public static String getValidAbcString(Scanner scan)
        //validate choice to return the correct alphabetic String else give input again
    {
        boolean StringValidated;
        String abcString;
        do
            {
                System.out.println("Enter a valid alphabetic value:");
                abcString = scan.nextLine();
                StringValidated = validateAbcString(abcString);
            }
        while (StringValidated == false);
        return abcString;
    }

}