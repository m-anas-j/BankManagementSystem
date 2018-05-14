import java.util.Date;

public class TransactionInfo {

    private String transactionDate;
    private String transactionType;
    private int withdrawAccount;
    private int depositAccount;
    private int amount;

    public TransactionInfo()
    {

    }

    public TransactionInfo(String _transactionDate, String _transactionType, int _withdrawAccount, int _depositAccount, int _amount)
    {
        transactionType = _transactionType;
        withdrawAccount = _withdrawAccount;
        depositAccount = _depositAccount;
        amount = _amount;
        StringBuilder sb = new StringBuilder();
        sb.append(_transactionDate);
        transactionDate = sb.toString();
        System.out.println(sb.toString() + " " + transactionType + " " + withdrawAccount + " " + depositAccount + " " + amount);
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(int withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public int getDepositAccount() {
        return depositAccount;
    }

    public void setDepositAccount(int depositAccount) {
        this.depositAccount = depositAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
