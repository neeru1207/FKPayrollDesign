package Tests;

import Classes.SalariedEmployee;
import Classes.SalesReceipt;
import Enums.PaymentMethods;

public class TestSalesReceipt
{
    public static void main(String[] args) throws Exception
    {
        SalariedEmployee emp = new SalariedEmployee("Testemp1", PaymentMethods.PICKED_UP_FROM_PAYMASTER, 10, (float) 4.5, (float) 4.5);
        SalariedEmployee emp2 = new SalariedEmployee("Testemp2", PaymentMethods.PICKED_UP_FROM_PAYMASTER, 1000, 0);
        // Valid cases
        SalesReceipt card1 = new SalesReceipt(emp, 9);
        SalesReceipt card2 = new SalesReceipt(emp, 6);
        System.out.println("Card 1 payment: "+ card1.getPayment());
        System.out.println("Card 2 payment: "+ card2.getPayment());
        // Invalid cases
        try
        {
            SalesReceipt card4 = new SalesReceipt(emp2, 4);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            SalesReceipt card3 = new SalesReceipt(emp, -4);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
