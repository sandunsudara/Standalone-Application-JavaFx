package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserRegistrationController implements Initializable {

    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    public BorderPane borderPane;
    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private PasswordField fldConfirmPassword;

    @FXML
    private TextField fldEmail;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private TextField fldShowConfirmPassword;

    @FXML
    private TextField fldShowPassword;

    @FXML
    private TextField fldUsername;

    @FXML
    private TextField txtAdminEmail;

    @FXML
    private TextField txtOTP;

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnCheckAction(ActionEvent event) {

    }

    @FXML
    void btnCreateAction(ActionEvent event) {

    }

    @FXML
    void btnRequestOTPAction(ActionEvent event) {

    }

    @FXML
    void showPasswordAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
