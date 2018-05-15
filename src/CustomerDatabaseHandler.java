import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int verifyLogin(int enteredCustomerId, String enteredPassword)
    {
        try
        {
            callableStatement = con.prepareCall("{CALL LOGIN_PROCEDURE(?,?,?)}");
            callableStatement.registerOutParameter(3,Types.NUMERIC);
            callableStatement.setInt(1,enteredCustomerId);
            callableStatement.setString(2,enteredPassword);
            callableStatement.execute();
            int result = callableStatement.getInt(3);
            System.out.println(result);
            return result;
        }catch(SQLException e)
        {
            System.out.println("Exception in login function " + e);
            return 0;
        }
    }

    public int verifyPin(int enteredCustomerId, int enteredPin)
    {
        System.out.println("Inside verifyPin function " + enteredCustomerId + " " + enteredPin);
        try
        {
            callableStatement = con.prepareCall("{CALL VERIFY_PIN(?,?,?)}");
            callableStatement.registerOutParameter(3,Types.NUMERIC);
            callableStatement.setInt(1,enteredCustomerId);
            callableStatement.setInt(2,enteredPin);
            callableStatement.execute();
            int result = callableStatement.getInt(3);
            System.out.println(result);
            return result;
        }
        catch (SQLException e)
        {
            System.out.println("Exception in verifyPin function " + e);
            return 0;
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

    public int withdraw(int accountNumber, int amount)
    {
        try
        {
            callableStatement = con.prepareCall("{CALL WITHDRAW(?,?,?)}");
            callableStatement.registerOutParameter(3,Types.NUMERIC);
            callableStatement.setInt(1,accountNumber);
            callableStatement.setInt(2,amount);
            callableStatement.execute();
            int total = callableStatement.getInt(3);
            System.out.println(total);
            return total;
        }catch(SQLException e)
        {
            System.out.println("Exception in withdraw function " + e);
            return 0;
        }
    }

    public int transfer(int transferrer, int receiver, int amount)
    {
        try
        {
            callableStatement = con.prepareCall("{CALL TRANSFER_FUNDS(?,?,?,?,?)}");
            callableStatement.registerOutParameter(4,Types.NUMERIC);
            callableStatement.registerOutParameter(5,Types.NUMERIC);
            callableStatement.setInt(1,transferrer);
            callableStatement.setInt(2,receiver);
            callableStatement.setInt(3,amount);
            callableStatement.execute();
            int transferrerTotal = callableStatement.getInt(4);
            int receiverTotal = callableStatement.getInt(5);
            System.out.println(transferrerTotal);
            return transferrerTotal;
        }catch(SQLException e)
        {
            System.out.println("Exception in transfer function " + e);
            return 0;
        }
    }

    public int checkBalance(int selectedAccount)
    {
        int balance = 0;
        try
        {
            callableStatement = con.prepareCall("{CALL CHECK_BALANCE(?,?)}");
            callableStatement.registerOutParameter(2,Types.NUMERIC);
            callableStatement.setInt(1,selectedAccount);
            callableStatement.execute();
            balance = callableStatement.getInt(2);
        }
        catch(SQLException e)
        {
            System.out.println("Exception in checkBalance function " + e);
            balance = -1;
        }
        return balance;
    }

    public ArrayList<String> getCustomerInfo(int accountId)
    {
        ArrayList<String> accountInformationList = new ArrayList<>();

        try
        {
            String query = "SELECT NAME, DATE_OF_SIGN_UP, NATIONALITY, GENDER, RELIGION, MOBILE_NUMBER FROM CUSTOMER WHERE CUSTOMER_ID = " + accountId;
            result = statement.executeQuery(query);
            while(result.next())
            {
                /*accountInformationList.add(result.getString(0));
                accountInformationList.add(result.getString(1));
                accountInformationList.add(result.getString(2));
                accountInformationList.add(result.getString(3));
                accountInformationList.add(result.getString(4));
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(result.getInt(5));*/
                accountInformationList.add(result.getString(1));
                accountInformationList.add(result.getDate(2).toString());
                accountInformationList.add(result.getString(3));
                accountInformationList.add(result.getString(4));
                accountInformationList.add(result.getString(5));
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(result.getInt(6));

                accountInformationList.add(sb.toString());
            }
        }
        catch(SQLException e)
        {

        }
        return accountInformationList;
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

    public ObservableList<TransactionInfo> getTransactionInfo(int accountNumber)
    {
        ObservableList<TransactionInfo> transactions = FXCollections.observableArrayList();

        String date;
        String type = "";
        int withdraw = 0;
        int deposit = 0;
        int amount = 0;
        try
        {
            String sql = "SELECT TRANSACTION_DATE, TRANSACTION_TYPE, ACCOUNT_WITHDRAWN_FROM, ACCOUNT_DEPOSITED_TO, TRANSACTION_AMOUNT FROM TRANSACTION WHERE ACCOUNT_WITHDRAWN_FROM = " + accountNumber + " OR ACCOUNT_DEPOSITED_TO = " + accountNumber;
            //result = statement.executeQuery("SELECT TRANSACTION_DATE, TRANSACTION_TYPE, WITHDRAW_ACCOUNT, DEPOSIT_ACCOUNT, AMOUNT FROM TRANSACTION WHERE WITHDRAW_ACCOUNT = " + accountNumber + " OR DEPOSIT_ACCOUNT = " + accountNumber);
            System.out.println(sql);
            result = statement.executeQuery(sql);
            while (result.next())
            {
                date = result.getString(1);
                type = result.getString(2);
                withdraw = result.getInt(3);
                deposit = result.getInt(4);
                amount = result.getInt(5);
                transactions.add(new TransactionInfo(date, type, withdraw, deposit, amount));
                //System.out.println(date + " " + type + " " + withdraw + " " + deposit + " " + amount);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Exception in getTransactionInfo function " + e);
        }
        return transactions;
    }

    public void clearMemory() throws SQLException
    {
        con.close();
        statement.close();
        System.out.println("Thank you for taking care of me <3");
    }


}
