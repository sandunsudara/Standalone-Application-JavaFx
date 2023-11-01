package controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Supplier;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.tableModel.SupplierTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class SupplierController implements Initializable {

    public TreeTableColumn colEmail;
    public TreeTableColumn colCompany;
    public ImageView wrongImg;
    private Scene preScene;

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label lblId;

    @FXML
    private TreeTableColumn<?, ?> colAddress;

    @FXML
    private TreeTableColumn<?, ?> colContact;

    @FXML
    private TreeTableColumn<?, ?> colDescription;

    @FXML
    private TreeTableColumn<?, ?> colId;

    @FXML
    private TreeTableColumn<?, ?> colItemCode;

    @FXML
    private TreeTableColumn<?, ?> colName;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private TreeTableColumn<?, ?> colQty;

    @FXML
    private TextField fldCompany;

    @FXML
    private TextField fldContact;

    @FXML
    private TextField fldEmail;

    @FXML
    private TextField fldSearch;


    @FXML
    private TextField fldSupplierName;

    @FXML
    private JFXTreeTableView<SupplierTm> supplierTable;

    @FXML
    private JFXTreeTableView<?> suppliesTables;

    @FXML
    void btnAddAction(ActionEvent event) {
        Supplier customer=Supplier
                .builder()
                .supplierId(lblId.getText())
                .supplierName(fldSupplierName.getText())
                .email(fldEmail.getText())
                .company(fldCompany.getText())
                .contact(fldContact.getText())
                .build();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="INSERT INTO supplier VALUES(?,?,?,?,?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1,customer.getSupplierId());
            pstm.setString(2,customer.getSupplierName());
            pstm.setString(3,customer.getEmail());
            pstm.setString(4,customer.getCompany());
            pstm.setString(5,customer.getContact());

            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved...!").show();
                clearFields();
                loadTableSupplier();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong...!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, new RuntimeException(e).getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnSearchAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        Supplier supplier=Supplier
                .builder()
                .supplierId(lblId.getText())
                .supplierName(fldSupplierName.getText())
                .email(fldEmail.getText())
                .company(fldCompany.getText())
                .contact(fldContact.getText())
                .build();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE supplier SET supplierName=?, email=?, company=? , contact=? WHERE supplierId=?");
            pstm.setString(1,supplier.getSupplierName());
            pstm.setString(2,supplier.getEmail());
            pstm.setString(3,supplier.getCompany());
            pstm.setString(4,supplier.getContact());
            pstm.setString(5,supplier.getSupplierId());
            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated...!").show();
                loadTableSupplier();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Somthing Went Wrong...!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateId(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1;");
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                int num= Integer.parseInt(resultSet.getString(1).split("[S]")[1]);
                num++;
                lblId.setText(String.format("S%03d",num));
            }
            else {
                lblId.setText("S001");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        generateId();
        fldSupplierName.clear();
        fldSupplierName.clear();
        fldEmail.clear();
        fldCompany.clear();
        fldContact.clear();
    }

    private void loadTableSupplier() {
        ObservableList<SupplierTm> tmList= FXCollections.observableArrayList();
        try {
            List<Supplier> list=new ArrayList<>();
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                list.add(Supplier.builder()
                        .supplierId(resultSet.getString(1))
                        .supplierName(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .company(resultSet.getString(4))
                        .contact(resultSet.getString(5))
                        .build());
            }
            for (Supplier supplier:list){
                JFXButton button=new JFXButton("DELETE");
                button.setStyle("-fx-pref-width: 100; -fx-pref-height: 30; -fx-background-color: red; -fx-border-radius: 15; -fx-font-size: 15px; -fx-font-weight: 800; -fx-text-fill: black; -fx-border-color: black; -fx-background-radius: 15; -fx-border-width: 2px;" );
                button.setOnAction(actionEvent -> {
                    try {
                        PreparedStatement pstm1= connection.prepareStatement("DELETE FROM supplier WHERE supplierId=?");
                        pstm1.setString(1,supplier.getSupplierId());
                        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete " + supplier.getSupplierId() + " Customer", ButtonType.YES, ButtonType.NO).showAndWait();
                        if (buttonType.get() == ButtonType.YES){
                            if (pstm1.executeUpdate()>0){
                                new Alert(Alert.AlertType.INFORMATION,"This \""+supplier.getSupplierId()+"\" Supplier Deleted..!").show();
                                loadTableSupplier();
                                generateId();
                            }
                            else {
                                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted..!").show();
                                loadTableSupplier();
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                tmList.add(SupplierTm.builder()
                        .supplierId(supplier.getSupplierId())
                        .supplierName(supplier.getSupplierName())
                        .email(supplier.getEmail())
                        .company(supplier.getCompany())
                        .contact(supplier.getContact())
                        .btn(button)
                        .build());
            }
            TreeItem<SupplierTm> treeI = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
            supplierTable.setRoot(treeI);
            supplierTable.setShowRoot(false);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate(TreeItem<SupplierTm> newValue) {
        lblId.setText(newValue.getValue().getSupplierId());
        fldSupplierName.setText(newValue.getValue().getSupplierName());
        fldEmail.setText(newValue.getValue().getEmail());
        fldCompany.setText(newValue.getValue().getCompany());
        fldContact.setText(newValue.getValue().getContact());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongImg.setVisible(false);
        colId.setCellValueFactory(new TreeItemPropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new TreeItemPropertyValueFactory<>("supplierName"));
        colEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new TreeItemPropertyValueFactory<>("company"));
        colContact.setCellValueFactory(new TreeItemPropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        generateId();
        loadTableSupplier();

        supplierTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue!= null){
                setDate(newValue);
            }
        } );

        fldContact.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });

        fldSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                supplierTable.setPredicate(new Predicate<TreeItem<SupplierTm>>() {
                    @Override
                    public boolean test(TreeItem<SupplierTm> supplierTmTreeItem) {
                        boolean flag = supplierTmTreeItem.getValue().getSupplierId().contains(newValue) || supplierTmTreeItem.getValue().getSupplierName().contains(newValue);
                        return flag;
                    }
                });

            }
        });

        fldContact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                try {
                    if (! oldValue.equals(newValue)) {
                        if (newValue.charAt(0) == '0' & newValue.charAt(1) == '7') {
                            wrongImg.setVisible(false);
                        } else {
                            wrongImg.setVisible(true);
                        }
                    }
                    else {
                        wrongImg.setVisible(false);
                    }
                }
                catch (Exception e){
                    System.out.println("Enter complete number");
                }


            }
        });
    }


    public void keyPressedOnContact(KeyEvent keyEvent) {
        int length=fldContact.getLength();
            if(length<10){
                fldContact.setEditable(true);
            }
            else if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.BACK_SPACE){
                fldContact.setEditable(true);
            }
            else {
                fldContact.setEditable(false);
            }
    }
}
