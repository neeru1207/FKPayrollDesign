// Test with Tests/TestSalariedEmployee
package Classes;

import Enums.PaymentMethods;
import java.time.LocalDate;

final public class SalariedEmployee extends Employee
{
    private int salary;
    private final boolean canReceiveCommissions;
    private float totalSales = 0;
    float commissionRate = 0;

    // CONSTRUCTORS

    // A constructor when the empId is present
    public SalariedEmployee (String empName, float totalSales, int empId, PaymentMethods meth, int salary, LocalDate joiningDate, float unionrate, float commissionRate) throws Exception
    {
        super(empName, empId, meth, joiningDate, unionrate);
        if (salary == 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero!");
        }
        if (commissionRate < 0) throw new Exception("ERROR! The Commission rate cannot be negative!");
        if (commissionRate != 0)
        {
            this.canReceiveCommissions = true;
            this.commissionRate = commissionRate;
        }
        else this.canReceiveCommissions = false;
        this.salary = salary;
        this.totalSales = totalSales;
    }

    // A constructor when the empId is not present
    public SalariedEmployee (String empName, float totalSales, PaymentMethods meth, int salary, LocalDate joiningDate, float unionrate, float commissionRate) throws Exception
    {
        super(empName, meth, joiningDate, unionrate);
        if (salary == 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero!");
        }
        if (commissionRate < 0) throw new Exception("ERROR! The Commission rate cannot be negative!");
        if (commissionRate != 0)
        {
            this.canReceiveCommissions = true;
            this.commissionRate = commissionRate;
        }
        else this.canReceiveCommissions = false;
        this.salary = salary;
        this.totalSales = totalSales;
    }

    // A function to change the salary
    public final void changeSalary(int salary) throws Exception
    {
        if (salary==0) throw new Exception("ERROR! The salary cannot be zero!");
        this.salary = salary;
    }
    // A function to get the salary
    public final int getSalary()
    {
        return this.salary;
    }

    // A function to return the commission rate
    public final float getCommissionRate()
    {
        return this.commissionRate;
    }

    // A function to check if the employee can receive commissions
    public final boolean canReceiveCommissions()
    {
        return this.canReceiveCommissions;
    }

    // Get the total sales
    public final float getTotalSales()
    {
        return this.totalSales;
    }
    public final void addToSales(float sales) throws Exception
    {
        if (sales<0) throw new Exception("Error!The sales cannot be negative!");
        this.totalSales += sales;
    }
    // Reset sales after payment for them is done
    public final void resetSales()
    {
        this.totalSales = 0;
    }
    // A function to display the details of the employees
    @Override
    public void displayDetails()
    {
        super.displayDetails();
        System.out.println("Monthly Paid");
        System.out.println("Salary: "+ this.salary);
        System.out.println("Total Sales: "+this.getTotalSales());
        System.out.println("Commission Rate: "+this.getCommissionRate());
    }
}
