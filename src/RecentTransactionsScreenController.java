import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class RecentTransactionsScreenController implements Initializable{



    @FXML TableView<TransactionInfo> transactionTable = new TableView<>();
    @FXML Scene recentTransactionsScene = new Scene(transactionTable);

    @FXML TableColumn<TransactionInfo,String> transactionDateColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,String> transactionTypeColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> withdrawAccountColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> depositAccountColumn = new TableColumn<>();
    @FXML TableColumn<TransactionInfo,Integer> transactionAmountColumn = new TableColumn<>();

    public void setRecentTransactionsScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RecentTransactionsScreen.fxml"));
        recentTransactionsScene = new Scene(root,800,600);
        recentTransactionsScene.setUserAgentStylesheet("TransactionTableStyle.css");
    }

    public Scene getRecentTransactionsScene()
    {
        return recentTransactionsScene;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");

        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        withdrawAccountColumn.setCellValueFactory(new PropertyValueFactory<>("withdrawAccount"));

        depositAccountColumn.setCellValueFactory(new PropertyValueFactory<>("depositAccount"));

        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        transactionTable.setItems(customerDatabaseHandler.getTransactionInfo(Main.currentLoggedInAccount));

    }
}
