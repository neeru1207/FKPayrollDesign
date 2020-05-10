package Tests;

import Classes.HourlyEmployee;
import Classes.TimeCard;
import Enums.PaymentMethods;

public class TestTimeCard
{
    public static void main(String[] args) throws Exception
    {
        HourlyEmployee emp = new HourlyEmployee("Testemp1", PaymentMethods.PICKED_UP_FROM_PAYMASTER, (float) 4.5);
        // Valid cases
        TimeCard card1 = new TimeCard(emp, 9);
        TimeCard card2 = new TimeCard(emp, 6);
        System.out.println("Card 1 payment: "+ card1.getPayment());
        System.out.println("Card 2 payment: "+ card2.getPayment());
        // Invalid case
        try
        {
            TimeCard card3 = new TimeCard(emp, -4);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
