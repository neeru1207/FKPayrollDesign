// Test file to test if Employee.java is functioning properly
package Tests;
import Classes.Employee;
import Enums.PaymentMethods;
class TestEmployee
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("......... Testing Employee.java .........");
        System.out.println("*****Test 1*****");
        // Invalid case
        try
        {
            Employee emp1 = new Employee("123", PaymentMethods.MAILED);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        // Valid cases
        System.out.println("*****Test 2*****");
        Employee emp2 = new Employee("Neeru", PaymentMethods.DEPOSITED_IN_BANK, (float) 3.4);
        emp2.displayDetails();
        System.out.println("****Changing the chosen payment method to Mailed****");
        emp2.changeChosenPaymentMethod(PaymentMethods.MAILED);
        emp2.displayDetails();
        System.out.println("*****Test 3*****");
        Employee emp3 = new Employee("Neeru", PaymentMethods.DEPOSITED_IN_BANK);
        emp3.displayDetails();

    }
}