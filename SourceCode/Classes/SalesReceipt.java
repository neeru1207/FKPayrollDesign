package Classes;

import java.time.LocalDate;

public class SalesReceipt
{
    private final SalariedEmployee emp;
    private final LocalDate date;
    private final int sales;

    // Constructors
    public SalesReceipt(SalariedEmployee emp, int sales, LocalDate date) throws Exception
    {
        if (!emp.canReceiveCommissions()) throw new Exception("ERROR! This employee cannot receive commissions!");
        if (sales < 0)throw new Exception("ERROR! The sales cannot be negative!");
        this.emp = emp;
        this.sales = sales;
        this.date = date;
    }

    // Without date
    public SalesReceipt(SalariedEmployee emp, int sales) throws Exception
    {
        this(emp, sales, LocalDate.now());
    }

    // A function to get the sales
    public final int getSales()
    {
        return this.sales;
    }

    // A function to get the date
    public final LocalDate getDate()
    {
        return this.date;
    }

    // A function to get the payment
    public final float getPayment()
    {
        return this.sales * this.emp.getCommissionRate();
    }
}

