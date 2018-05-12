import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {



    @FXML static Stage mainWindow = new Stage();

    @FXML static WelcomeScreenController welcomeScreen = new WelcomeScreenController();
    @FXML static SignUpScreenController signUpScreen = new SignUpScreenController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        mainWindow = primaryStage;
        welcomeScreen.setWelcomeScene();
        primaryStage.setTitle("Hello");
        //Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        //primaryStage.setScene(new Scene(root,800,600));
        primaryStage.setScene(welcomeScreen.getWelcomeScene());
        primaryStage.show();


    }
}
