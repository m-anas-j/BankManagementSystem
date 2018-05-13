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
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeScreenController implements Initializable {

    public WelcomeScreenController()
    {

    }


    @FXML AnchorPane firstAnchorPane = new AnchorPane();
    @FXML Scene welcomeScene = new Scene(firstAnchorPane);
    @FXML Button leftButton = new Button();
    @FXML Button rightButton = new Button();
    @FXML TextField userNameTextField = new TextField();
    @FXML TextField passWordTextField = new TextField();
    @FXML Button loginButton = new Button();
    @FXML Button signupButton = new Button();
    @FXML ImageView myImageView = new ImageView();
    @FXML ImageView bankImageView = new ImageView();
    @FXML ImageView rightArrow = new ImageView();
    @FXML ImageView leftArrow = new ImageView();

    public void setWelcomeScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        welcomeScene = new Scene(root,1000,900);
        welcomeScene.setUserAgentStylesheet("TestStyle.css");
    }
    public Scene getWelcomeScene()
    {
        return welcomeScene;
    }

    @FXML void leftButtonClicked()
    {
        //leftButton.setText("Left");
        File file = new File("src/Assets/439494.jpg");
        Image image = new Image(file.toURI().toString());
        myImageView.setImage(image);
    }

    @FXML void rightButtonClicked()
    {
        //rightButton.setText("Right");
        File file = new File("src/Assets/439235.jpg");
        Image image = new Image(file.toURI().toString());
        myImageView.setImage(image);
    }

    @FXML public void loginbuttonClicked() throws IOException
    {
        Main.featuresScreen.setFeaturesScene();
        Main.mainWindow.setScene(Main.featuresScreen.getFeaturesScene());
        Main.mainWindow.show();
    }

    @FXML void signUpButtonClicked() throws IOException
    {
        Main.signUpScreenContinued.setSignUpSceneContinued();
        Main.mainWindow.setScene(Main.signUpScreenContinued.getSignUpSceneContinued());
        Main.mainWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/Assets/439494.jpg");
        Image myImage = new Image(file.toURI().toString());
        myImageView.setImage(myImage);

        File file1 = new File("src/Assets/left_icon.png");
        Image leftImage = new Image(file1.toURI().toString());
        leftArrow.setImage(leftImage);

        File file2 = new File("src/Assets/right_icon.png");
        Image rightImage = new Image(file2.toURI().toString());
        rightArrow.setImage(rightImage);

        rightButton.getStyleClass().add("button-right-left");
        leftButton.getStyleClass().add("button-right-left");

        signupButton.getStyleClass().add("button-blue");
    }
}
