import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TransferFundsFeature implements Initializable{

    @FXML VBox firstVbox = new VBox();
    @FXML AnchorPane firstAnchorPane = new AnchorPane();
    @FXML Scene transferFundsScene = new Scene(firstVbox);
    @FXML TextField amountField = new TextField();
    @FXML PasswordField pinField = new PasswordField();
    @FXML TextField updatedTransferrerBalance = new TextField();
    @FXML TextField updatedReceiverBalance = new TextField();
    @FXML ComboBox<String> withdrawAccountList = new ComboBox<>();
    @FXML TextField receivingAccountField = new TextField();
    @FXML Button dismissButton = new Button();
    @FXML Button confirmButton = new Button();

    public void setTransferFundsScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("TransferFunds.fxml"));
        transferFundsScene = new Scene(root,800,600);
        transferFundsScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getTransferFundsScene()
    {
        return transferFundsScene;
    }

    @FXML public void confirmButtonClicked()
    {
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        int transferrerTotal = customerDatabaseHandler.withdraw(Main.currentLoggedInAccount,Integer.parseInt(amountField.getText()));
        if (transferrerTotal==-1)
        {
            updatedTransferrerBalance.setText("Insufficient balance");
        }
        else
        {
            updatedTransferrerBalance.setText(Integer.toString(transferrerTotal));
            int receiverTotal = customerDatabaseHandler.deposit(Integer.parseInt(receivingAccountField.getText()),Integer.parseInt(amountField.getText()));
            updatedReceiverBalance.setText(Integer.toString(receiverTotal));
        }

        confirmButton.setVisible(false);
    }

    @FXML public void dismissButtonClicked()
    {
        Main.featureWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmButton.getStyleClass().add("button-blue");

        ArrayList<String> listOfAccounts = new ArrayList<>();
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        listOfAccounts = customerDatabaseHandler.listAllAccountOfCustomer(Main.currentLoggedInCustomer);
        withdrawAccountList.getItems().addAll(listOfAccounts);
    }
}
