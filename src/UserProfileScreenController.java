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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserProfileScreenController implements Initializable{

    @FXML private ComboBox<String> accountList = new ComboBox<>();

    @FXML private TextField customerNameField = new TextField();

    @FXML private TextField registrationDateField = new TextField();

    @FXML private TextField nationalityField = new TextField();

    @FXML private TextField genderField = new TextField();

    @FXML private TextField religionField = new TextField();

    @FXML private TextField mobileNumberField = new TextField();
    @FXML private TextField customerIdField = new TextField();

    @FXML private Button dismissButton = new Button();

    HBox firstHbox = new HBox();
    Scene userProfileScene = new Scene(firstHbox);

    public UserProfileScreenController()
    {

    }

    public void setUserProfileScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("UserProfileScreen.fxml"));
        userProfileScene = new Scene(root,800,600);
        userProfileScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getUserProfileScene()
    {
        return userProfileScene;
    }

    @FXML public void dismissButtonClicked()
    {
        Main.featureWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try
        {
            CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
            ArrayList<String> accountInformationList = new ArrayList<>();
            accountInformationList = customerDatabaseHandler.getCustomerInfo(Main.currentLoggedInCustomer);


            customerIdField.setText(Integer.toString(Main.currentLoggedInCustomer));
            System.out.println(customerIdField.getText());
            customerNameField.setText(accountInformationList.get(0));
            registrationDateField.setText(accountInformationList.get(1));
            nationalityField.setText(accountInformationList.get(2));
            genderField.setText(accountInformationList.get(3));
            religionField.setText(accountInformationList.get(4));
            mobileNumberField.setText(accountInformationList.get(5));

            ArrayList<String> listOfAccounts  = customerDatabaseHandler.listAllAccountOfCustomer(Main.currentLoggedInCustomer);
            accountList.getItems().addAll(listOfAccounts);
            customerDatabaseHandler.clearMemory();
        }
        catch(SQLException e)
        {
            System.out.println("Exception in clearing memory in userprofile class initialize method");
        }
    }
}
