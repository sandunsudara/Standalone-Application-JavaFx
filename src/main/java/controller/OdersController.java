package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OdersController implements Initializable {

    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    @FXML
    private ComboBox<?> SelectItemDescription;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TreeTableColumn<?, ?> colAmount;

    @FXML
    private TreeTableColumn<?, ?> colCode;

    @FXML
    private TreeTableColumn<?, ?> colDescription;

    @FXML
    private TreeTableColumn<?, ?> colDiscount;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TreeTableColumn<?, ?> colType;

    @FXML
    private TreeTableColumn<?, ?> colUnitePrice;

    @FXML
    private ComboBox<?> employerSelectById;

    @FXML
    private ComboBox<?> employerSelectByName;

    @FXML
    private TextField fldCash;

    @FXML
    private TextField fldCustomer;

    @FXML
    private TextField fldCustomerContact;

    @FXML
    private DatePicker fldDate;

    @FXML
    private TextField fldDiscount;

    @FXML
    private TextField fldcustomerEmail;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSize;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblType;

    @FXML
    private ComboBox<?> selectItemCode;

    @FXML
    private Spinner<?> spinnerQty;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnBackAction(ActionEvent event) {
        Stage stage= (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
