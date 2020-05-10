package Classes;

import java.io.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import Enums.PaymentMethods;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.DocFlavor;

public class PayRoll
{
    private int numberOfEmployees;
    private HashMap<Integer, SalariedEmployee> salariedEmployeeMap = new HashMap<>();
    private HashMap<Integer, HourlyEmployee> hourlyEmployeeMap = new HashMap<>();
    public static String employeeDetailsFilePath = "./FKPayrollDesign/SourceCode/JSON/employeedetails.json";
    public static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    @SuppressWarnings("unchecked")
    private void openPayRoll()
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(PayRoll.employeeDetailsFilePath))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            //Iterate over employee array and add them to the employee hashmap
            employeeList.forEach( emp -> {
                try {
                    this.parseEmployeeObject( (JSONObject) emp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public PayRoll() throws Exception
    {
        this.openPayRoll();
    }
    public void addEmployee(SalariedEmployee emp)
    {
        this.salariedEmployeeMap.put(emp.getEmpId(), emp);
    }
    public void addEmployee(HourlyEmployee emp)
    {
        this.hourlyEmployeeMap.put(emp.getEmpId(), emp);
    }
    public void addEmployee() throws Exception
    {
        float unionrate = 0;
        String name;
        LocalDate joiningDate;
        boolean belongsToUnion;
        System.out.println("*****Add Employee*****");
        System.out.println("Name:");
        name = buf.readLine();
        System.out.println("Joining Date:");
        joiningDate = LocalDate.parse(buf.readLine());
        System.out.println("Belongs to Union? True(1) or False(0)?");
        belongsToUnion = Integer.parseInt(buf.readLine()) != 0;
        if (belongsToUnion) {
            System.out.println("Enter the Union Dues Rate:");
            unionrate = Float.parseFloat(buf.readLine());
        }
        System.out.println("Choose Payment Method:\n1.Send Paycheck to Mail\n2.Pick up from Paymaster\n3.Deposit to Bank Account");
        PaymentMethods method;
        switch (Integer.parseInt(buf.readLine())) {
            case 1:
                method = PaymentMethods.MAILED;
                break;
            case 2:
                method = PaymentMethods.PICKED_UP_FROM_PAYMASTER;
                break;
            default:
                method = PaymentMethods.DEPOSITED_IN_BANK;
                break;
        }
        System.out.println("Employee type? 0 for Salaried Employee, 1 for Hourly Employee:");
        switch (Integer.parseInt(buf.readLine())) {
            case 0:
                System.out.println("Salary: ");
                int salary = Integer.parseInt(buf.readLine());
                System.out.println("Sales commissions rate: ");
                int commissionRate = Integer.parseInt(buf.readLine());
                SalariedEmployee emp = new SalariedEmployee(name, 0, method, salary, joiningDate, unionrate, commissionRate);
                this.salariedEmployeeMap.put(emp.getEmpId(), emp);
                break;
            default:
                System.out.println("Hourly Rate: ");
                int hourlyRate = Integer.parseInt(buf.readLine());
                HourlyEmployee emp1 = new HourlyEmployee(name, 0, method, hourlyRate, joiningDate, unionrate);
                this.hourlyEmployeeMap.put(emp1.getEmpId(), emp1);
                break;
        }
    }

    public void deleteEmployee() throws IOException
    {
        System.out.println("Enter the Employee ID:");
        int emptype = Integer.parseInt(buf.readLine());
        if (salariedEmployeeMap.remove(emptype) == null)
        {
            if (hourlyEmployeeMap.remove(emptype) == null)
                System.out.println("Can't remove! No such employee present!");
        }
    }
    public void displayAllEmployees()
    {
        System.out.println("Displaying all employees!");
        for (Map.Entry<Integer, SalariedEmployee> mapElement : this.salariedEmployeeMap.entrySet())
        {
            mapElement.getValue().displayDetails();
        }
        for (Map.Entry<Integer, HourlyEmployee> mapElement : this.hourlyEmployeeMap.entrySet())
        {
            mapElement.getValue().displayDetails();
        }
    }

    public void postTimeCard(LocalDate date, int empid, int numberOfHours) throws Exception
    {
        if (this.hourlyEmployeeMap.get(empid)==null) return;
        HourlyEmployee objref = this.hourlyEmployeeMap.get(empid);
        TimeCard tim = new TimeCard(objref, date, numberOfHours);
        objref.addToTotalWages(tim.getPayment());
    }

    public static LocalDate getLastWorkingDayOfMonth(LocalDate lastDayOfMonth) {
        LocalDate lastWorkingDayofMonth;
        switch (DayOfWeek.of(lastDayOfMonth.get(ChronoField.DAY_OF_WEEK))) {
            case SATURDAY:
                lastWorkingDayofMonth = lastDayOfMonth.minusDays(1);
                break;
            case SUNDAY:
                lastWorkingDayofMonth = lastDayOfMonth.minusDays(2);
                break;
            default:
                lastWorkingDayofMonth = lastDayOfMonth;
        }
        return lastWorkingDayofMonth;
    }

    public void runPayRoll(LocalDate date)
    {
        System.out.println("Payroll running........");
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastWorkDayCurrentMonth=getLastWorkingDayOfMonth(lastDayofCurrentMonth);
        // Have to pay all the employees
        if (lastWorkDayCurrentMonth.isEqual(date))
        {
            System.out.println("Last working day of the month!");
            System.out.println("Paying appropriate employees...");
            //Pay employees
        }
        if (date.getDayOfWeek() == DayOfWeek.FRIDAY)
        {
            System.out.println("It's Friday!");
            System.out.println("Paying Hourly Employees their due wages and Salaried Employees their due commissions");
            for (Map.Entry<Integer, SalariedEmployee> mapElement : this.salariedEmployeeMap.entrySet())
            {
                SalariedEmployee obj = mapElement.getValue();
                obj.resetSales();
            }
            for (Map.Entry<Integer, HourlyEmployee> mapElement : this.hourlyEmployeeMap.entrySet())
            {
                HourlyEmployee obj = mapElement.getValue();
                obj.resetTotalWages();
            }
            System.out.println("Done!");
        }
    }
    public void runPayRoll()
    {
        this.runPayRoll(LocalDate.now());
    }
    public void postSalesReceipt(LocalDate date, int empid, float sales)
    {
        if (this.salariedEmployeeMap.get(empid)==null) return;
        SalariedEmployee emp = this.salariedEmployeeMap.get(empid);
        try
        {
            SalesReceipt rec = new SalesReceipt(emp, sales, date);
            emp.addToSales(rec.getSales());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void parseEmployeeObject(JSONObject employee) throws Exception
    {
        Long tempx ;
        Double tempx2;
        tempx = (Long) employee.get("EmpType");
        int emptype = tempx.intValue();
        String name = (String) employee.get("Name");
        tempx = (Long) employee.get("EmpId");
        int empId = tempx.intValue();
        tempx2 = (Double) employee.get("UnionDuesRate");
        float unionDuesRate = tempx2.floatValue();
        tempx2 = (Double) employee.get("ServiceCharges");
        float serviceCharge =  tempx2.floatValue();
        PaymentMethods meth = PaymentMethods.valueOf((String) employee.get("PaymentMethod"));
        LocalDate joiningDate = LocalDate.parse((String) employee.get("JoiningDate"));
        // Salaried Employee
        if (emptype == 0)
        {
            tempx2 = (Double) employee.get("CommissionRate");
            float commissionRate = tempx2.floatValue();
            tempx = (Long) employee.get("Salary");
            int salary = tempx.intValue();
            tempx2 = (Double) employee.get("TotalSales");
            float totalsales = tempx2.floatValue();
            SalariedEmployee newemp = new SalariedEmployee(name, totalsales, empId, meth, salary, joiningDate, unionDuesRate, commissionRate);
            this.salariedEmployeeMap.put(empId, newemp);
        }
        else
        {
            tempx2 = (Double) employee.get("HourlyRate");
            float hourlyRate = tempx2.floatValue();
            tempx2 = (Double) employee.get("TotalWages");
            float totalwages = tempx2.floatValue();
            HourlyEmployee newemp = new HourlyEmployee(name, totalwages, meth, hourlyRate, joiningDate, unionDuesRate);
            this.hourlyEmployeeMap.put(empId, newemp);
        }
    }

    // Function to close the payroll and write the updated data to the JSON file
    @SuppressWarnings("unchecked")
    public void closePayRoll()
    {
        // Here the type of salaried employee is 0 and the type of Hourly employee is 1
        JSONObject tmpobj;
        JSONArray employeeList = new JSONArray();
        for (Map.Entry<Integer, SalariedEmployee> mapElement : this.salariedEmployeeMap.entrySet())
        {
            int empId = mapElement.getKey();
            SalariedEmployee empobj = mapElement.getValue();
            tmpobj = new JSONObject();
            tmpobj.put("Name", empobj.getEmpName());
            tmpobj.put("EmpType", 0);
            tmpobj.put("EmpId", empobj.getEmpId());
            tmpobj.put("JoiningDate", empobj.getJoiningDate().toString());
            tmpobj.put("UnionDuesRate", empobj.getUnionDuesRate());
            tmpobj.put("PaymentMethod", empobj.getChosenPaymentMethod().toString());
            tmpobj.put("ServiceCharges", empobj.getServiceCharge());
            tmpobj.put("Salary", empobj.getSalary());
            tmpobj.put("CommissionRate", empobj.getCommissionRate());
            tmpobj.put("TotalSales", empobj.getTotalSales());
            employeeList.add(tmpobj);

        }
        for (Map.Entry<Integer, HourlyEmployee> mapElement : this.hourlyEmployeeMap.entrySet())
        {
            int empId = mapElement.getKey();
            HourlyEmployee empobj = mapElement.getValue();
            tmpobj = new JSONObject();
            tmpobj.put("Name", empobj.getEmpName());
            tmpobj.put("EmpType", 0);
            tmpobj.put("EmpId", empobj.getEmpId());
            tmpobj.put("JoiningDate", empobj.getJoiningDate().toString());
            tmpobj.put("UnionDuesRate", empobj.getUnionDuesRate());
            tmpobj.put("PaymentMethod", empobj.getChosenPaymentMethod().toString());
            tmpobj.put("ServiceCharges", empobj.getServiceCharge());
            tmpobj.put("HourlyRate", empobj.getHourlyRate());
            tmpobj.put("TotalWages", empobj.getTotalWages());
            employeeList.add(tmpobj);
        }
        //Write to the JSON file
        try (FileWriter file = new FileWriter(PayRoll.employeeDetailsFilePath))
        {
            file.write(employeeList.toJSONString());
            file.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Employee.writePresentEmpId();
    }
}
