import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpScreenContinuedController implements Initializable{
    @FXML TextField userIdField = new TextField();
    @FXML PasswordField passwordField = new PasswordField();
    @FXML PasswordField confirmPasswordField = new PasswordField();
    @FXML PasswordField pinField = new PasswordField();
    @FXML PasswordField confirmPinField = new PasswordField();
    @FXML GridPane firstGridPane = new GridPane();
    @FXML Scene signUpSceneContinued = new Scene(firstGridPane);
    @FXML TextField nameField = new TextField();
    @FXML DatePicker dateOfBirthField = new DatePicker();
    @FXML TextField nationalityField = new TextField();
    @FXML ComboBox<String> genderField = new ComboBox<>();
    @FXML TextField addressField = new TextField();
    @FXML TextField religionField = new TextField();
    @FXML TextField mobileNumberField = new TextField();
    @FXML Button generateCustomerIdButton = new Button();
    @FXML Button notInterestedButton = new Button();
    boolean generateCustomerButtonState = true;

    public void setSignUpSceneContinued() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpScreenContinued.fxml"));
        signUpSceneContinued = new Scene(root,1000,900);
        signUpSceneContinued.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getSignUpSceneContinued()
    {
        return signUpSceneContinued;
    }


    @FXML public void notInterestedButtonClicked()
    {
        Main.mainWindow.setScene(Main.welcomeScreen.getWelcomeScene());
        Main.mainWindow.show();
    }


    @FXML public void generateUserIdClicked() throws SQLException
    {
        try
        {
            if (generateCustomerButtonState && passwordField.getText().length()>=6 && pinField.getText().length()==4)
            {
                String name = nameField.getText();
                String dob = dateOfBirthField.getValue().toString();
                String nationality = nationalityField.getText();
                String gender = genderField.getValue();
                String address = addressField.getText();
                String religion = religionField.getText();
                String password = passwordField.getText();
                String pin = pinField.getText();
                int mobileNumber = Integer.parseInt(mobileNumberField.getText());
                CustomerDatabaseHandler customerDatabaseHandler = new CustomerDatabaseHandler("bankmanagementsystem","bankmanagementsystem");
                //customerDatabaseHandler.addNewCustomer("anas","27/08/97","bd","Male","CTG","Current Account","Muslim",null,123,"Nein?");
                //long cust_id = customerDatabaseHandler.addNewCustomer("anas","2018/12/25","nationality","Male","ctg","Islam",12);
                long cust_id = customerDatabaseHandler.addNewCustomer(name,dob,nationality,gender,address,religion,mobileNumber,password,pin);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(cust_id);
                userIdField.setText(sb.toString());
                customerDatabaseHandler.clearMemory();

                if (passwordField.getText().length()<6 || pinField.getText().length()!=4 || passwordField.getText()!=confirmPasswordField.getText() || pinField.getText()!=confirmPinField.getText())
                {
                    System.out.println(passwordField.getText() + " " + confirmPasswordField.getText());
                    if (passwordField.getText().length() < 6) {
                        passwordField.clear();
                        passwordField.setPromptText("Must be atleast 6 characters!");
                    } else if (pinField.getText().length() != 4) {
                        pinField.clear();
                        pinField.setPromptText("Must be 4 digits!");
                    }
                }
            /*else if (passwordField.getText()!=confirmPasswordField.getText())
            {
                confirmPasswordField.clear();
                confirmPasswordField.setPromptText("Passwords do not match!");
            }
            else if (pinField.getText()!=confirmPinField.getText())
            {
                confirmPinField.clear();
                pinField.setPromptText("PINs do not match!");
            }*/


                //set every field to false editable
                nameField.setEditable(false);
                dateOfBirthField.setEditable(false);
                nationalityField.setEditable(false);
                genderField.setEditable(false);
                addressField.setEditable(false);
                religionField.setEditable(false);
                mobileNumberField.setEditable(false);
                passwordField.setEditable(false);
                confirmPasswordField.setEditable(false);
                pinField.setEditable(false);
                confirmPinField.setEditable(false);

                //change generate button
                generateCustomerIdButton.getStyleClass().add("button-blue");
                generateCustomerIdButton.setText("Continue");
                generateCustomerButtonState = false;
            }
        /*else if (passwordField.getText().length()<6 || pinField.getText().length()!=4 || passwordField.getText()!=confirmPasswordField.getText() || pinField.getText()!=confirmPinField.getText())
        {
            System.out.println(passwordField.getText() + " " + confirmPasswordField.getText());
            if(passwordField.getText().length()<6)
            {
                passwordField.clear();
                passwordField.setPromptText("Must be atleast 6 characters!");
            }
            else if (pinField.getText().length()!=4)
            {
                pinField.clear();
                pinField.setPromptText("Must be 4 digits!");
            }
            else if (passwordField.getText()!=confirmPasswordField.getText())
            {
                confirmPasswordField.clear();
                confirmPasswordField.setPromptText("Passwords do not match!");
            }
            else if (pinField.getText()!=confirmPinField.getText())
            {
                confirmPinField.clear();
                pinField.setPromptText("PINs do not match!");
            }
        }*/
            else
            {
                generateCustomerButtonState = true;
                try
                {
                    Main.currentLoggedInCustomer = Integer.parseInt(userIdField.getText());
                    String generatedCustomerId = userIdField.getText();
                    SignUpScreenController.generatedCustomerId = generatedCustomerId;
                    Main.signUpScreen.setSignUpScene(generatedCustomerId);
                    Main.mainWindow.setScene(Main.signUpScreen.getSignUpScene());
                    Main.mainWindow.show();
                }catch (IOException e)
                {

                }
            }
        }
        catch (NumberFormatException e)
        {
            if (passwordField.getText().length()<6 || pinField.getText().length()!=4 || passwordField.getText()!=confirmPasswordField.getText() || pinField.getText()!=confirmPinField.getText())
            {
                System.out.println(passwordField.getText() + " " + confirmPasswordField.getText());
                if (passwordField.getText().length() < 6) {
                    passwordField.clear();
                    passwordField.setPromptText("Must be atleast 6 characters!");
                } else if (pinField.getText().length() != 4) {
                    pinField.clear();
                    pinField.setPromptText("Must be 4 digits!");
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set default value to dpb
        dateOfBirthField.setValue(LocalDate.now());
        //set values to gender
        genderField.getItems().addAll("Male","Female");
        genderField.setStyle("-fx-font: 50px;");

    }
}
