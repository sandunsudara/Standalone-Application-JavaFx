package controller;

import DB.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogingFormController implements Initializable {


    @FXML
    private TextField fldShowPassword;

    @FXML
    private BorderPane loginFromBorder;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox checkBox;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private TextField fldUsername;

    @FXML
    void clearBtnAction(ActionEvent event) {
        this.showPasswordAction(null);
        fldUsername.clear();
        fldPassword.clear();
        fldShowPassword.clear();

    }

    @FXML
    void forgotPasswordBtnAction(ActionEvent event) {
        Stage stage= (Stage) loginFromBorder.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/FrogetPassword.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void loginBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        loginAction();
    }

    @FXML
    void showPasswordAction(ActionEvent event) {
        if (checkBox.isSelected()){
            fldShowPassword.setText(fldPassword.getText());
            fldShowPassword.setVisible(true);
            fldPassword.setVisible(false);
            return;
        }
        else {
            fldPassword.setText(fldShowPassword.getText());
            fldPassword.setVisible(true);
            fldShowPassword.setVisible(false);
        }
    }

    @FXML
    void signUpBtnAction(ActionEvent event) {
        Stage stage= (Stage) loginFromBorder.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserRegistration.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }


    private void loginAction() throws SQLException, ClassNotFoundException, IOException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT password , type FROM user WHERE username='"+fldUsername.getText()+"';");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            if (fldShowPassword.getText().equals(resultSet.getString(1)) || fldPassword.getText().equals(resultSet.getString(1))){
                Stage stage= (Stage) loginFromBorder.getScene().getWindow();
                if (resultSet.getString(2).equals("Admin")) {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoard.fxml"))));
                }
                else {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserDashBoard.fxml"))));
                }
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Incorrect Password ...!").show();
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Username Not Found ...!").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showPasswordAction(null);

    }

    public void enterKey(javafx.scene.input.KeyEvent keyEvent) throws SQLException, IOException, ClassNotFoundException {
        loginAction();
        System.out.println("dsdfs");
    }
}
