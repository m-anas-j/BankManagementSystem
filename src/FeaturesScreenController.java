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

import java.io.IOException;

public class FeaturesScreenController {
    BorderPane firstBorderPane = new BorderPane();
    Scene featuresScene = new Scene(firstBorderPane);

    public void setFeaturesScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FeaturesScreen.fxml"));
        featuresScene = new Scene(root,1000,800);
        featuresScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getFeaturesScene()
    {
        return featuresScene;
    }
}
