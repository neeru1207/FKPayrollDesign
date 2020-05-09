// Test file to test if HourlyEmployee.java is functioning properly
package Tests;
import Classes.Employee;
import Classes.HourlyEmployee;
import Enums.PaymentMethods;
class TestHourlyEmployee
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("......... Testing Hourly Employee.java .........");
        System.out.println("*****Test 1*****");
        // Invalid cases
        try
        {
            Employee emp1 = new HourlyEmployee("123", PaymentMethods.MAILED, 10, 0);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("*****Test 2*****");
        try
        {
            Employee emp1 = new HourlyEmployee("Neeru", PaymentMethods.MAILED, 0, 0);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        // Valid cases
        System.out.println("*****Test 3*****");
        HourlyEmployee emp2 = new HourlyEmployee("Neeru", PaymentMethods.DEPOSITED_IN_BANK, 4, (float) 3.4);
        emp2.displayDetails();
        System.out.println("****Changing the chosen payment method to Mailed****");
        emp2.changeChosenPaymentMethod(PaymentMethods.MAILED);
        emp2.displayDetails();
        System.out.println("*****Test 4*****");
        HourlyEmployee emp3 = new HourlyEmployee("Neeru", PaymentMethods.DEPOSITED_IN_BANK, 100, 0);
        emp3.displayDetails();

    }
}