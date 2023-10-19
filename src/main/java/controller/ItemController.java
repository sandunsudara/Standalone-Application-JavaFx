package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private TreeTableColumn<?, ?> colBuyingPrice;

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colDescription;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colSellyingPrice;

    @FXML
    private TreeTableColumn<?, ?> colSize;

    @FXML
    private TreeTableColumn<?, ?> colSupplierId;

    @FXML
    private TreeTableColumn<?, ?> colType;

    @FXML
    private TextField fldAddQty;

    @FXML
    private TextField fldBuyPrice;

    @FXML
    private TextField fldDescription;

    @FXML
    private TextField fldItemCode;

    @FXML
    private TextField fldQty;

    @FXML
    private TextField fldSearch;

    @FXML
    private TextField fldSellyingPrice;

    @FXML
    private ComboBox<?> selectSize;

    @FXML
    private ComboBox<?> selectType;

    @FXML
    private ComboBox<?> supplierSelectById;

    @FXML
    private ComboBox<?> supplierSelectByName;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnAddQtyAction(ActionEvent event) {

    }

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnSaveAction(ActionEvent event) {

    }

    @FXML
    void btnSearchAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnPlaceOrderAction(ActionEvent actionEvent) {
    }
}
