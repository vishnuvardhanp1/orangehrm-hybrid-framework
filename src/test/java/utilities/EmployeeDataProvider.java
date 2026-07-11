package utilities;


import org.testng.annotations.DataProvider;

public class EmployeeDataProvider {

    @DataProvider(name="EmployeeData")
    public Object[][] employeeData(){

        return ExcelUtility.getEmployeeData("EmployeeSheet");

    }

}
