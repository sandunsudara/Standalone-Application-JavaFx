package controller;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployerController {
    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private TreeTableColumn<?, ?> colAddress;

    @FXML
    private TreeTableColumn<?, ?> colContact;

    @FXML
    private TreeTableColumn<?, ?> colId;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private TextField fldAddress;

    @FXML
    private TextField fldContact;

    @FXML
    private TextField fldEmail;

    @FXML
    private TextField fldEmployeeId;

    @FXML
    private TextField fldEmployeeName;

    @FXML
    private TextField fldSearch;

    @FXML
    private JFXTreeTableView<?> table;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnClearAction(ActionEvent event) {
        fldEmployeeId.clear();
        fldEmployeeName.clear();
        fldEmail.clear();
        fldAddress.clear();
        fldContact.clear();
        fldSearch.clear();

    }

    @FXML
    void btnSearchAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

}
