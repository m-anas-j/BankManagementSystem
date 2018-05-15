import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RecentTransactionsScreenController implements Initializable{



    @FXML TableView<TransactionInfo> transactionTable = new TableView<>();
    @FXML Scene recentTransactionsScene = new Scene(transactionTable);
    @FXML Button confirmButton = new Button();
    @FXML Button dismissButton = new Button();
    @FXML ComboBox<String> accountList = new ComboBox<>();

    @FXML TableColumn<TransactionInfo,String> transactionDateColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,String> transactionTypeColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> withdrawAccountColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> depositAccountColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> transactionAmountColumn = new TableColumn<>();

    public void setRecentTransactionsScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RecentTransactionsScreen.fxml"));
        recentTransactionsScene = new Scene(root,800,600);
        recentTransactionsScene.setUserAgentStylesheet("TestStyle.css");
    }

    @FXML public void confirmButtonClicked()
    {
        try
        {
            Main.currentLoggedInAccount = Integer.parseInt(accountList.getValue());

            CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");

            transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

            transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

            withdrawAccountColumn.setCellValueFactory(new PropertyValueFactory<>("withdrawAccount"));

            depositAccountColumn.setCellValueFactory(new PropertyValueFactory<>("depositAccount"));

            transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

            transactionTable.setItems(customerDatabaseHandler.getTransactionInfo(Main.currentLoggedInAccount));

            customerDatabaseHandler.clearMemory();
        }
        catch (SQLException e)
        {

        }
    }

    @FXML public void dismissButtonClicked()
    {
        Main.featureWindow.close();
    }

    public Scene getRecentTransactionsScene()
    {
        return recentTransactionsScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        confirmButton.getStyleClass().add("button-blue");

        ArrayList<String> listOfAccounts = new ArrayList<>();
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        listOfAccounts = customerDatabaseHandler.listAllAccountOfCustomer(Main.currentLoggedInCustomer);
        accountList.getItems().addAll(listOfAccounts);

        try
        {
            customerDatabaseHandler.clearMemory();
        }
        catch (SQLException e)
        {

        }
    }
}
