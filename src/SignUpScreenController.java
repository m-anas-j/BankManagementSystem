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
    @FXML TextField nameField = new TextField();
    @FXML DatePicker dateOfBirthField = new DatePicker();
    @FXML TextField nationalityField = new TextField();
    @FXML ComboBox<String> genderField = new ComboBox<>();
    @FXML TextField addressField = new TextField();
    @FXML ComboBox<String> accountTypeField = new ComboBox<>();
    @FXML TextField casteField = new TextField();
    @FXML TextField accountNumberField = new TextField();
    @FXML TextField mobileNumberField = new TextField();
    @FXML ComboBox<String> securityQuestionField = new ComboBox<>();
    @FXML Button generateAccountNumber = new Button();
    @FXML int getGenerateAccountNumberState = 1;



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
        if (getGenerateAccountNumberState==1)
        {
            String name = nameField.getText();
            String dob = dateOfBirthField.getValue().toString();
            String nationality = nationalityField.getText();
            String gender = genderField.getValue();
            String address = addressField.getText();
            String accountType = accountTypeField.getValue();
            String caste = casteField.getText();
            int mobileNumber = Integer.parseInt(mobileNumberField.getText());
            String securityQues = securityQuestionField.getValue();
            CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
            //customerDatabaseHandler.addNewCustomer("anas","27/08/97","bd","Male","CTG","Current Account","Muslim",null,123,"Nein?");
            long acc_num = customerDatabaseHandler.addNewCustomer(name, dob, nationality, gender, address, accountType, caste, null, mobileNumber, securityQues);
            customerDatabaseHandler.clearMemory();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(acc_num);

            //show generated account number
            accountNumberField.setText(stringBuilder.toString());
            //set every field to uneditable
            nameField.setEditable(false);
            dateOfBirthField.setEditable(false);
            nationalityField.setEditable(false);
            genderField.setEditable(false);
            addressField.setEditable(false);
            accountTypeField.setEditable(false);
            casteField.setEditable(false);
            accountNumberField.setEditable(false);
            mobileNumberField.setEditable(false);
            securityQuestionField.setEditable(false);

            //change generate button
            generateAccountNumber.getStyleClass().add("button-blue");
            generateAccountNumber.setText("Continue;");
            getGenerateAccountNumberState = 0;
        }
        else
        {
            try
            {
                getGenerateAccountNumberState = 1;
                Main.featuresScreen.setFeaturesScene();
                Main.mainWindow.setScene(Main.featuresScreen.getFeaturesScene());
                Main.mainWindow.show();
            }catch (IOException e)
            {

            }

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstGridPane.setPadding(new Insets(10,10,10,10));
        firstGridPane.setHgap(8);
        firstGridPane.setVgap(10);

        //set default value to dpb
        dateOfBirthField.setValue(LocalDate.now());
        //set values to gender
        genderField.getItems().addAll("Male","Female");
        genderField.setStyle("-fx-font: 50px;");
        //set values to account type
        accountTypeField.getItems().addAll("Savings Account","Current Account","Student Account");
        //set security questions
        securityQuestionField.getItems().addAll("What is your pets name?","What is your favourite dessert?","Who is your favourite footballer?");


    }
}
