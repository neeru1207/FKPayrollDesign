// Test with Tests/TestHourlyEmployee
package Classes;

import Enums.PaymentMethods;
import java.time.LocalDate;

final public class HourlyEmployee extends Employee
{
    private float hourlyRate;
    private float totalWages;
    // CONSTRUCTORS

    // Constructor when the empId is provided
    public HourlyEmployee (String empName, int empId, float totalWages, PaymentMethods meth, float hourlyRate, LocalDate joiningDate, float unionrate) throws Exception
    {
        super(empName, empId, meth, joiningDate, unionrate);
        if (hourlyRate <= 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero or negative!");
        }
        this.hourlyRate = hourlyRate;
        this.totalWages = totalWages;
    }
    // Constructor when the empId is NOT provided
    public HourlyEmployee (String empName, float totalWages, PaymentMethods meth, float hourlyRate, LocalDate joiningDate, float unionrate) throws Exception
    {
        super(empName, meth, joiningDate, unionrate);
        if (hourlyRate <= 0)
        {
            throw new Exception("ERROR! The hourly rate cannot be zero or negative!");
        }
        this.hourlyRate = hourlyRate;
        this.totalWages = totalWages;
    }

    // Function to set the hourly rate.
    public final void setHourlyRate(float rate) throws Exception
    {
        if (rate==0) throw new Exception("ERROR! The hourly rate cannot be zero!");
        this.hourlyRate = rate;
    }

    // Function to get the hourly rate.
    public final float getHourlyRate()
    {
        return this.hourlyRate;
    }

    // Function to get the total wages
    public final float getTotalWages()
    {
        return this.totalWages;
    }

    // Add to total wages to be paid when a time card is used.
    public void addToTotalWages(float amt) throws Exception
    {
        if (amt<0) throw new Exception("ERROR! The added wage cannot be zero!");
        this.totalWages += amt;
    }

    // Reset the wages to 0 when the wages are paid on the payment day.
    public void resetTotalWages()
    {
        this.totalWages = 0;
    }

    @Override
    public void displayDetails()
    {
        super.displayDetails();
        System.out.println("Hourly Paid");
        System.out.println("Hourly rate: "+ this.hourlyRate);
        System.out.println("Total Wages yet to be paid: "+ this.getTotalWages());
    }
}
