package Classes;

import java.time.LocalDate;

public class TimeCard
{
    private final HourlyEmployee emp;
    private final LocalDate date;
    private final int numberOfHours;
    // Constructors
    public TimeCard(HourlyEmployee emp, LocalDate date, int numberOfHours) throws Exception
    {
        if (numberOfHours < 0) throw new Exception("ERROR! The number of hours cannot be negative!");
        this.emp = emp;
        this.date = date;
        this.numberOfHours = numberOfHours;
    }
    // If date is not provided, use the current date.
    public TimeCard(HourlyEmployee emp, int numberOfHours) throws Exception
    {
        this(emp, LocalDate.now(), numberOfHours);
    }
    public LocalDate getDate()
    {
        return this.date;
    }
    public int getNumberOfHours()
    {
        return this.numberOfHours;
    }
    public float getPayment()
    {
        return this.numberOfHours > 8? (float) ((1.5 * (this.numberOfHours - 8) + 8) * this.emp.getHourlyRate()) : (float) (this.numberOfHours * this.emp.getHourlyRate());
    }
}
