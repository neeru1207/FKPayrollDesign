// Test with Tests/TestHourlyEmployee
package Classes;
import Enums.PaymentMethods;
import java.time.LocalDate;
final public class HourlyEmployee extends Employee
{
    private int hourlyRate;

    // CONSTRUCTORS
    // @CompulsoryParams : Employee Name, Payment method, Hourly Rate
    // @DefaultParams : Union Rate = 0, Joining date = Present date

    // Add to Employee Union and custom joining date Constructor.
    public HourlyEmployee (String empName, PaymentMethods meth, int hourlyRate, LocalDate joiningDate, float unionrate) throws Exception
    {
        super(empName, meth, joiningDate, unionrate);
        if (hourlyRate == 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero!");
        }
        this.hourlyRate = hourlyRate;
    }

    // Present joining date and don't add to Employee Union Constructor.
    public HourlyEmployee (String empName, PaymentMethods meth, int hourlyRate) throws Exception
    {
        this(empName, meth, hourlyRate, LocalDate.now());
    }

    // Custom joining date and don't add to Employee Union Constructor.
    public HourlyEmployee (String empName, PaymentMethods meth, int hourlyRate, LocalDate joiningDate) throws Exception
    {
       this(empName, meth, hourlyRate, joiningDate, 0);
    }

    // Present joining date and add to Employee Union Constructor
    public HourlyEmployee (String empName, PaymentMethods meth, int hourlyRate, float unionrate) throws Exception
    {
        this(empName, meth, hourlyRate, LocalDate.now(), unionrate);
    }

    // Function to set the hourly rate.
    public void setHourlyRate(int rate) throws Exception
    {
        if (rate==0) throw new Exception("ERROR! The hourly rate cannot be zero!");
        this.hourlyRate = rate;
    }

    // Function to get the hourly rate.
    public int getHourlyRate()
    {
        return this.hourlyRate;
    }

    @Override
    public void displayDetails()
    {
        super.displayDetails();
        System.out.println("Hourly Paid");
        System.out.println("Hourly rate: "+ this.hourlyRate);
    }
}
