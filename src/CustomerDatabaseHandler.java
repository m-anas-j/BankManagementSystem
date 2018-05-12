import java.io.File;
import java.sql.*;

public class CustomerDatabaseHandler {

    Connection con=null;
    Statement statement;
    static ResultSet result;
    File file = new File("input.txt");

    public CustomerDatabaseHandler(String username, String password)
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE",username,password);
            statement=con.createStatement();
            System.out.println("Connection to database successful");
        }
        catch(Exception e)
        {
            System.out.println("Error while connecting to database "+e);
        }
    }

    public long addNewCustomer(String name, String dob, String nationality, String gender, String address, String accountType, String caste, String accountNumber, int mobileNumber, String secQues)
    {
        long acc_num=0;
        String insertQuery = "INSERT INTO CUSTOMER VALUES ( '" + name + "', SYSDATE, " + "TO_DATE('" + dob + "','yyyy/mm/dd'), '" + nationality + "', '" + gender + "', '" + address + "', '" + accountType + "', '" + caste + "', null, " + mobileNumber + ", '" + secQues + "')";
        System.out.println(insertQuery);
        try
        {
            statement.executeUpdate(insertQuery);
            statement.executeUpdate("COMMIT");
            result = statement.executeQuery("SELECT MAX(ACCOUNT_NUMBER) FROM CUSTOMER");
            while(result.next())
            {
                acc_num = result.getLong(1);
            }
            return acc_num;
        }
        catch(SQLException e)
        {
            System.out.println("addNewCustomer function " + e);
            return 0;
        }
    }

    public void clearMemory() throws SQLException
    {
        con.close();
        statement.close();
        System.out.println("Thank you for taking care of me <3");
    }


}
