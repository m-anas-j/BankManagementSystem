import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmBoxController implements Initializable{

    @FXML private static Label confirmBoxText = new Label();
    @FXML private static Button yesButton = new Button();
    @FXML private static Button noButton = new Button();
    @FXML static BorderPane borderPane = new BorderPane();
    @FXML static Scene confirmBoxScene = new Scene(borderPane);



    public void setConfirmBoxScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ConfirmBox.fxml"));
        confirmBoxScene = new Scene(root, 320,240);
        confirmBoxScene.setUserAgentStylesheet("TestStyle.css");

    }

    public Scene getConfirmBoxScene(){
        return confirmBoxScene;
    }

    @FXML public void yesButtonClicked() {
        Main.confirmBoxWindow.close();
        Main.mainWindow.close();
    }

    @FXML public void noButtonClicked(){
        Main.confirmBoxWindow.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noButton.getStyleClass().add("button-blue");
    }
}
