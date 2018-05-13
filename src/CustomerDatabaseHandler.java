import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDatabaseHandler {

    Connection con=null;
    Statement statement;
    static ResultSet result;
    CallableStatement callableStatement;

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

    public long addNewAccount(String name, String accountType, String accountNumber, int mobileNumber, String secQues, String customerId)
    {
        long acc_num=0;
        String insertQuery = "INSERT INTO ACCOUNT VALUES ( '" + name + "', SYSDATE, '" + accountType + "', " +  null + ", " +   mobileNumber + ", '" + secQues + "', 0, " + customerId + ")" ;
        System.out.println(insertQuery);
        try
        {
            statement.executeUpdate(insertQuery);
            statement.executeUpdate("COMMIT");
            result = statement.executeQuery("SELECT ACCOUNT_NUMBER FROM ACCOUNT");
            while(result.next())
            {
                acc_num = result.getLong(1);
            }
            return acc_num;
        }
        catch(SQLException e)
        {
            System.out.println("addNewAccount function " + e);
            return 0;
        }
    }

    public ArrayList<String> listAllAccountOfCustomer(int customerId)
    {
        ArrayList<String> accountList = new ArrayList<>();
        try
        {
            String query = "SELECT ACCOUNT_NUMBER FROM ACCOUNT WHERE CUSTOMER_ID = " + customerId;
            result = statement.executeQuery(query);
            while(result.next())
            {
                accountList.add(result.getString(1));
            }
        }catch (SQLException e)
        {
            System.out.println("Exception in listAllAccountOfCustomer function " + e);
        }
        return accountList;
    }

    public int deposit(int accountNumber, int amount)
    {
        try
        {
            callableStatement = con.prepareCall("{CALL DEPOSIT(?,?,?)}");
            callableStatement.registerOutParameter(3,Types.NUMERIC);
            callableStatement.setInt(1,accountNumber);
            callableStatement.setInt(2,amount);
            callableStatement.execute();
            int total = callableStatement.getInt(3);
            System.out.println(total);
            return total;
        }catch(SQLException e)
        {
            System.out.println("Exception in deposit function " + e);
            return 0;
        }
    }

    public long addNewCustomer(String name, String dob, String nationality, String gender, String address, String religion, int mobileNumber, String password, String pin)
    {
        long custNum=0;
        //String insertQuery = "INSERT INTO CUSTOMER VALUES (NULL, '" + name + "', SYSDATE " +  ", TO_DATE('" + dob + "','yyyy/mm/dd'), '" + nationality + "', '" + gender + "', '" + address + "', '" + religion + "', " + mobileNumber + ")";
        String insertQuery = "INSERT INTO CUSTOMER VALUES (NULL, '" + name + "', SYSDATE, TO_DATE('" + dob + "','yyyy/mm/dd'), '" + nationality + "', '" + gender + "', '" + address + "', '" + religion + "', " + mobileNumber + ", '" + password + "', '" + pin + "')";
        System.out.println(insertQuery);

        try
        {
            statement.executeUpdate(insertQuery);
            statement.executeUpdate("COMMIT");
            System.out.println("Successfully inserted into CUSTOMER");
            result = statement.executeQuery("SELECT CUSTOMER_ID FROM CUSTOMER");
            while(result.next())
            {
                custNum = result.getLong(1);
            }
            return custNum;
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
