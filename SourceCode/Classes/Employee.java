//Test with ../Tests/TestEmployee
package Classes;

import Enums.PaymentMethods;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;

public class Employee
{
    final int empId;
    final String empName;
    final LocalDate joiningDate;
    final boolean belongsToUnion;
    static int presEmpId;
    public float unionDuesRate = 0;
    public float serviceCharge = 0;
    public PaymentMethods chosenPaymentMethod;
    final static public String empidfilepath = "./FKPayrollDesign/SourceCode/JSON/storepresempid.json";

    // A function to fetch the present empId from JSON file
    public static void fetchPresentEmpId() throws IOException, ParseException
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(empidfilepath))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject empidval = (JSONObject) obj;
            Long X = (Long) empidval.get("PresEmpId");
            presEmpId = X.intValue();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writePresentEmpId()
    {
        JSONObject obj = new JSONObject();
        obj.put("PresEmpId", presEmpId);
        try (FileWriter file = new FileWriter(Employee.empidfilepath))
        {
            file.write(obj.toJSONString());
            file.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    // CONSTRUCTORS

    // A constructor when the employee ID is provided
    public Employee(String empName, int empId, PaymentMethods meth, LocalDate joiningDate, float unionrate) throws Exception
    {
        fetchPresentEmpId();
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
        this.empId = empId;
        this.chosenPaymentMethod = meth;
        if (unionrate < 0) throw new Exception("ERROR! The Employee Union Due Rate cannot be negative!");
        if (unionrate != 0) this.belongsToUnion = true;
        else this.belongsToUnion = false;
        this.unionDuesRate = unionrate;
    }

    // Constructor when the employee ID is not provided
    public Employee(String empName, PaymentMethods meth, LocalDate joiningDate, float unionrate) throws Exception
    {
        fetchPresentEmpId();
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
        if (unionrate < 0) throw new Exception("ERROR! The Employee Union Due Rate cannot be negative!");
        if (unionrate != 0) this.belongsToUnion = true;
        else this.belongsToUnion = false;
        this.unionDuesRate = unionrate;
        Employee.presEmpId++;
        Employee.writePresentEmpId();
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

    final public PaymentMethods getChosenPaymentMethod()
    {
        return this.chosenPaymentMethod;
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
    final public void setServiceCharge(float serviceCharge)throws Exception
    {
        if (serviceCharge < 0) throw new Exception("Service charge rate cannot be negative!");
        this.serviceCharge = serviceCharge;
    }

    final public float getServiceCharge()
    {
        return this.serviceCharge;
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
        System.out.println("Service charges: " + this.serviceCharge);
    }
}