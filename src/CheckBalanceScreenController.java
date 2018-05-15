import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckBalanceScreenController implements Initializable{

    @FXML HBox firstHbox = new HBox();
    Scene checkBalanceScene = new Scene(firstHbox);
    @FXML Button confirmButton = new Button();
    @FXML Button dismissButton = new Button();
    @FXML ComboBox<String> accountList = new ComboBox<>();
    @FXML TextField balanceField = new TextField();

    public CheckBalanceScreenController()
    {

    }

    public void setCheckBalanceScene()  throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("CheckBalanceScreen.fxml"));
        checkBalanceScene = new Scene(root,800,600);
        checkBalanceScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getCheckBalanceScene()
    {
        return checkBalanceScene;
    }

    @FXML public void confirmButtonClicked()
    {
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        int balance = customerDatabaseHandler.checkBalance(Integer.parseInt(accountList.getValue().toString()));
        StringBuilder sb = new StringBuilder();
        sb.append(balance);
        balanceField.setText(sb.toString());
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
        accountList.getItems().addAll(listOfAccounts);

        //customerDatabaseHandler.clearMemory();
    }
}
