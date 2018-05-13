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

public class DepositScreenController implements Initializable{

    @FXML VBox firstVbox = new VBox();
    @FXML AnchorPane firstAnchorPane = new AnchorPane();
    @FXML Scene depositScene = new Scene(firstVbox);
    @FXML TextField amountField = new TextField();
    @FXML PasswordField pinField = new PasswordField();
    @FXML TextField updatedBalanceField = new TextField();
    @FXML ComboBox<String> depositAccountList = new ComboBox<>();
    @FXML Button dismissButton = new Button();
    @FXML Button confirmButton = new Button();

    public void setDepositScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("DepositScreen.fxml"));
        depositScene = new Scene(root,800,600);
        depositScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getDepositScene()
    {
        return depositScene;
    }

    @FXML public void confirmButtonClicked()
    {
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        int total = customerDatabaseHandler.deposit(Main.currentLoggedInAccount,Integer.parseInt(amountField.getText()));
        updatedBalanceField.setText(Integer.toString(total));

        confirmButton.setVisible(false);
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
        depositAccountList.getItems().addAll(listOfAccounts);

    }
}
