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

public class WithdrawScreenController implements Initializable{

    @FXML VBox firstVbox = new VBox();
    @FXML AnchorPane firstAnchorPane = new AnchorPane();
    @FXML Scene withdrawScene = new Scene(firstVbox);
    @FXML TextField amountField = new TextField();
    @FXML PasswordField pinField = new PasswordField();
    @FXML TextField updatedBalanceField = new TextField();
    @FXML ComboBox<String> withdrawAccountList = new ComboBox<>();
    @FXML Button dismissButton = new Button();
    @FXML Button confirmButton = new Button();

    public void setWithdrawScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("WithdrawScreen.fxml"));
        withdrawScene = new Scene(root,800,600);
        withdrawScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getWithdrawScene()
    {
        return withdrawScene;
    }

    @FXML public void confirmButtonClicked()
    {
        Main.currentLoggedInAccount = Integer.parseInt(withdrawAccountList.getValue());
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        int pinVerified = customerDatabaseHandler.verifyPin(Main.currentLoggedInCustomer,Integer.parseInt(pinField.getText()));
        if (pinVerified==1)
        {
            int total = customerDatabaseHandler.withdraw(Main.currentLoggedInAccount,Integer.parseInt(amountField.getText()));
            if (total==-1)
            {
                updatedBalanceField.setText("Insufficient balance");
            }
            else
                updatedBalanceField.setText(Integer.toString(total));

            confirmButton.setVisible(false);
        }
        else
        {
            pinField.clear();
            updatedBalanceField.setText("Invalid PIN");
        }

    }

    @FXML public void desmissButtonClicked()
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
