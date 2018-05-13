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
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeaturesScreenController implements Initializable{
    BorderPane firstBorderPane = new BorderPane();
    Scene featuresScene = new Scene(firstBorderPane);

    @FXML Button transferFundsButton = new Button();
    @FXML Button withdrawButton = new Button();
    @FXML Button depositButton = new Button();
    @FXML Button checkBalanceButton = new Button();
    @FXML Button generateStatementButton = new Button();
    @FXML Button currentInvestmentsButton = new Button();
    @FXML Button userProfileButton = new Button();

    public void setFeaturesScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FeaturesScreen.fxml"));
        featuresScene = new Scene(root,1000,900);
        featuresScene.setUserAgentStylesheet("TestStyle.css");
    }

    public Scene getFeaturesScene()
    {
        return featuresScene;
    }

    @FXML public void depositButtonClicked() throws IOException
    {
        Main.depositScreen.setDepositScene();
        Main.featureWindow.setScene(Main.depositScreen.getDepositScene());
        Main.featureWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transferFundsButton.getStyleClass().add("button-featuresscreen");
        withdrawButton.getStyleClass().add("button-featuresscreen");
        depositButton.getStyleClass().add("button-featuresscreen");
        checkBalanceButton.getStyleClass().add("button-featuresscreen");
        generateStatementButton.getStyleClass().add("button-featuresscreen");
        currentInvestmentsButton.getStyleClass().add("button-featuresscreen");
        userProfileButton.getStyleClass().add("button-featuresscreen");
        //transferFundsButton.setShape(new Circle(0,0,25));
    }
}
