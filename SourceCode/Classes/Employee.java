//Test with ../Tests/TestEmployee
package Classes;
import Enums.PaymentMethods;
import java.time.LocalDate;
public class Employee
{
    final int empId;
    final String empName;
    final LocalDate joiningDate;
    final boolean belongsToUnion;
    static int presEmpId = 1;
    public float unionDuesRate = 0;
    public float serviceChargeRate = 0;
    public PaymentMethods chosenPaymentMethod;

    // CONSTRUCTORS
    // @CompulsoryParams : Employee Name, Payment method, Salary
    // @DefaultParams : Union Rate = 0, Joining date = Present date

    // Add to Employee Union and custom joining date Constructor.
    public Employee(String empName, PaymentMethods meth, LocalDate joiningDate, float unionrate) throws Exception
    {
        if (empName == null || empName.isEmpty())
        {
            throw new Exception("ERROR! Employee name cannot be empty or null!");
        }
        if (!Character.isLetter(empName.charAt(0)))
        {
            throw new Exception("ERROR! Employee name cannot start with a non-alphabetic character!");
        }
        this.empName = empName;
        this.joiningDate = joiningDate;
        this.empId = Employee.presEmpId;
        this.chosenPaymentMethod = meth;
        if (unionrate != 0) this.belongsToUnion = true;
        else this.belongsToUnion = false;
        this.unionDuesRate = unionrate;
        Employee.presEmpId++;
    }

    // Present joining date and don't add to Employee Union Constructor.
    public Employee(String empName, PaymentMethods meth) throws Exception
    {
        this(empName, meth, LocalDate.now(), 0);
    }

    // Custom joining date and don't add to Employee Union Constructor.
    public Employee(String empName, PaymentMethods meth, LocalDate date) throws Exception
    {
        this(empName, meth, date, 0);
    }

    // Present joining date and add to Employee Union Constructor
    public Employee(String empName, PaymentMethods meth, float unionrate) throws Exception
    {
        this(empName, meth, LocalDate.now(), unionrate);
    }

    // A function to get the employee ID
    final public int getEmpId()
    {
        return this.empId;
    }

    // A function to get the employee name
    final public String getEmpName()
    {
        return this.empName;
    }

    // A function to check if the employee belongs to the employee union
    final public boolean belongsToUnion()
    {
        return this.belongsToUnion;
    }

    // A function to change the chosen payment method
    final public void changeChosenPaymentMethod(PaymentMethods meth)
    {
        this.chosenPaymentMethod = meth;
    }

    // A function to get the Union Dues Rate
    final public float getUnionDuesRate()
    {
        return this.unionDuesRate;
    }

    // A function to change the Union Dues Rate
    final public void setUnionDuesRate(float unionDuesRate)
    {
        if (unionDuesRate == 0 || this.unionDuesRate == 0) return;
        this.unionDuesRate = unionDuesRate;
    }

    // A function to get the joining date
    final public LocalDate getJoiningDate()
    {
        return this.joiningDate;
    }

    // A function to set the union service charges rate
    final public void setServiceChargeRate(float serviceChargeRate)throws Exception
    {
        if (serviceChargeRate < 0)throw new Exception("Service charge rate cannot be negative!");
        this.serviceChargeRate = serviceChargeRate;
    }

    final public float getServiceChargeRate()
    {
        return this.serviceChargeRate;
    }

    // A function to display the details of the employee
    public void displayDetails()
    {
        System.out.println("********EMPLOYEE DETAILS********");
        System.out.println("Employee name: " + this.empName);
        System.out.println("Employee ID: " + this.empId);
        System.out.println("Joining Date: " + this.joiningDate);
        System.out.println("Belongs to Union?: "+ (this.belongsToUnion()? "Yes" : "No"));
        System.out.println("Union Dues Rate: " + (this.unionDuesRate !=0? this.unionDuesRate : "NaN"));
        System.out.println("Preferred Payment Method: " + this.chosenPaymentMethod);
    }
}