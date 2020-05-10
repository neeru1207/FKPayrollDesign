import Classes.PayRoll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Main
{
    public static void main(String[] args) throws Exception {
        PayRoll payRoll = new PayRoll();
        BufferedReader buf = new BufferedReader(new InputStreamReader((System.in)));
        boolean switcher = true;
        while(switcher)
        {
        System.out.println("*****PayRoll Application");
        System.out.println("1.Add an Employee");
        System.out.println("2.Delete an Employee");
        System.out.println("3.Display all Employees");
        System.out.println("4.Post a TimeCard");
        System.out.println("5.Post a SalesReceipt");
        System.out.println("6.Run the Payroll");
        System.out.println("7.Exit\n");
        switch(Integer.parseInt(buf.readLine())) {
            case 1:
                payRoll.addEmployee();
                break;
            case 2:
                payRoll.deleteEmployee();
                break;
            case 3:
                payRoll.displayAllEmployees();
                break;
            case 4:
                System.out.println("Enter the Date (yyyy-mm-dd), Employee ID and Number of Hours");
                LocalDate date = LocalDate.parse(buf.readLine());
                int empid = Integer.parseInt(buf.readLine());
                int numHours = Integer.parseInt(buf.readLine());
                payRoll.postTimeCard(date, empid, numHours);
                System.out.println("TimeCard successfully posted!");
                break;
            case 5:
                System.out.println("Enter the Date (yyyy-mm-dd), Employee ID and Amount of sales");
                LocalDate date1 = LocalDate.parse(buf.readLine());
                int empid1 = Integer.parseInt(buf.readLine());
                float amtsales = Float.parseFloat(buf.readLine());
                payRoll.postSalesReceipt(date1, empid1, amtsales);
                break;
            case 6:
                System.out.println("Date:?");
                LocalDate date2 = LocalDate.parse(buf.readLine());
                payRoll.runPayRoll(date2);
                break;
            default:
                switcher = false;
                payRoll.closePayRoll();
                break;
        }

        }
    }
}
