// Test with Tests/TestSalariedEmployee
package Classes;
import Enums.PaymentMethods;

import java.time.LocalDate;
public class SalariedEmployee extends Employee
{
    private int salary;
    private final boolean canReceiveCommissions;
    float commissionRate = 0;

    // CONSTRUCTORS
    // @CompulsoryParams : Employee Name, Payment method, Salary
    // @CompulsoryParams : Commission rate (Pass 0 if the employee can't receive commissions.
    // @DefaultParams : Union Rate = 0, Joining date = Present date
    public SalariedEmployee (String empName, PaymentMethods meth, int salary, LocalDate joiningDate, float unionrate, float commissionRate) throws Exception
    {
        super(empName, meth, joiningDate, unionrate);
        if (salary == 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero!");
        }
        if (commissionRate != 0)this.canReceiveCommissions = true;
        else this.canReceiveCommissions = false;
        this.salary = salary;
    }

    public SalariedEmployee (String empName, PaymentMethods meth, int salary, float unionrate, float commissionRate) throws Exception
    {
        this(empName, meth, salary, LocalDate.now(), unionrate, commissionRate);
    }

    public SalariedEmployee (String empName, PaymentMethods meth, int salary, LocalDate joiningDate, float commissionRate) throws Exception
    {
        this(empName, meth, salary, joiningDate, 0, commissionRate);
    }

    public SalariedEmployee (String empName, PaymentMethods meth, int salary, float commissionRate) throws Exception
    {
        this(empName, meth, salary, LocalDate.now(), 0, commissionRate);
    }

    public void changeSalary(int salary) throws Exception
    {
        if (salary==0) throw new Exception("ERROR! The salary cannot be zero!");
        this.salary = salary;
    }
    public int getSalary()
    {
        return this.salary;
    }
    @Override
    public void displayDetails()
    {
        super.displayDetails();
        System.out.println("Monthly Paid");
        System.out.println("Salary: "+ this.salary);
    }
}
