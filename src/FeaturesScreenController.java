import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
    @FXML Button newBankAccountButton = new Button();
    @FXML Button signOutButton = new Button();

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

    @FXML public void withdrawButtonClicked() throws IOException
    {
        Main.withdrawScreen.setWithdrawScene();
        Main.featureWindow.setScene(Main.withdrawScreen.getWithdrawScene());
        Main.featureWindow.show();
    }

    @FXML public void checkBalanceButtonClicked() throws IOException
    {
        Main.checkBalanceScreen.setCheckBalanceScene();
        Main.featureWindow.setScene(Main.checkBalanceScreen.getCheckBalanceScene());
        Main.featureWindow.show();
    }

    @FXML public void transferFundsButtonClicked() throws IOException
    {
        Main.transferFundsFeature.setTransferFundsScene();
        Main.featureWindow.setScene(Main.transferFundsFeature.getTransferFundsScene());
        Main.featureWindow.show();
    }

    @FXML public void recentTransactionsButtonClicked() throws IOException
    {
        Main.recentTransactionsScreen.setRecentTransactionsScene();
        Main.featureWindow.setScene(Main.recentTransactionsScreen.getRecentTransactionsScene());
        Main.featureWindow.show();
    }

    @FXML public void userProfileButtonClicked() throws IOException
    {
        Main.userProfileScreen.setUserProfileScene();
        Main.featureWindow.setScene(Main.userProfileScreen.getUserProfileScene());
        Main.featureWindow.show();
    }

    @FXML public void newBankAccountButtonClicked() throws IOException
    {
        Main.signUpScreen.setSignUpScene(Integer.toString(Main.currentLoggedInCustomer));
        Main.mainWindow.setScene(Main.signUpScreen.getSignUpScene());
        Main.mainWindow.show();
    }

    @FXML public void signOutButtonClicked() throws IOException
    {
        Main.mainWindow.close();
        Main.mainWindow.setScene(Main.welcomeScreen.getWelcomeScene());
        Main.mainWindow.show();
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
        newBankAccountButton.getStyleClass().add("button-featuresscreen");
        //transferFundsButton.setShape(new Circle(0,0,25));
    }
}
