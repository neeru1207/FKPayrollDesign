// Test file to test if SalariedEmployee.java is functioning properly
package Tests;
import Classes.Employee;
import Classes.SalariedEmployee;
import Enums.PaymentMethods;
class TestSalariedEmployee
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("......... Testing Salaried Employee.java .........");
        System.out.println("*****Test 1*****");
        // Invalid cases
        try
        {
            Employee emp1 = new SalariedEmployee("123", PaymentMethods.MAILED, 10, 0);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("*****Test 2*****");
        try
        {
            Employee emp1 = new SalariedEmployee("Neeru", PaymentMethods.MAILED, 0, 0);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        // Valid cases
        System.out.println("*****Test 3*****");
        SalariedEmployee emp2 = new SalariedEmployee("Neeru", PaymentMethods.DEPOSITED_IN_BANK, 100, (float) 3.4);
        emp2.displayDetails();
        System.out.println("****Changing the chosen payment method to Mailed****");
        emp2.changeChosenPaymentMethod(PaymentMethods.MAILED);
        emp2.displayDetails();
        System.out.println("*****Test 4*****");
        SalariedEmployee emp3 = new SalariedEmployee("Neeru", PaymentMethods.DEPOSITED_IN_BANK, 100, 0);
        emp3.displayDetails();

    }
}