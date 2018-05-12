import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpScreenController implements Initializable{

    @FXML GridPane firstGridPane = new GridPane();
    @FXML Scene signUpScene = new Scene(firstGridPane);
    @FXML TextField name = new TextField();
    @FXML DatePicker dateOfBirth = new DatePicker();
    @FXML TextField nationality = new TextField();
    @FXML ComboBox<String> gender = new ComboBox<>();
    @FXML TextField address = new TextField();
    @FXML TextField accountType = new TextField();
    @FXML TextField caste = new TextField();
    @FXML TextField accountNumber = new TextField();
    @FXML TextField mobileNumber = new TextField();
    @FXML TextField securityQuestion = new TextField();
    @FXML Button generateAccountNumber = new Button();
    boolean isGenerated = false;



    public SignUpScreenController()
    {

    }


    public void setSignUpScreen() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        signUpScene = new Scene(root,1000,800);
        signUpScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getSignUpScene()
    {
        return signUpScene;
    }

    @FXML public void generateAccountNumberClicked() throws SQLException
    {
        CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
        customerDatabaseHandler.addNewCustomer("anas","bleh","27/08/97","bd","Male","CTG","Personal","Muslim",null,123,"Nein?");
        customerDatabaseHandler.clearMemory();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstGridPane.setPadding(new Insets(10,10,10,10));
        firstGridPane.setHgap(8);
        firstGridPane.setVgap(10);

        dateOfBirth.setValue(LocalDate.now());
        gender.getItems().addAll("Male","Female");
        gender.setStyle("-fx-font: 50px;");


    }
}
