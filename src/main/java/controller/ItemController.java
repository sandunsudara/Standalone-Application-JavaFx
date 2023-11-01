package controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Item;
import model.Supplier;
import model.tableModel.ItemTm;
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
import java.util.function.UnaryOperator;

public class ItemController implements Initializable {

    public Label lblid;
    public JFXTreeTableView <ItemTm> itemTable;
    public JFXButton btnAddQty;
    public JFXButton btnAdd;
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
    private ComboBox<String> selectSize;

    @FXML
    private ComboBox<String> selectType;

    @FXML
    private ComboBox<String> supplierSelectById;

    @FXML
    private ComboBox<String> supplierSelectByName;

    @FXML
    void btnAddAction(ActionEvent event) {
        Item item= Item.builder()
                .itemId(lblid.getText())
                .description(fldDescription.getText())
                .cost(Double.parseDouble(fldBuyPrice.getText()))
                .salesPrice(Double.parseDouble(fldSellyingPrice.getText()))
                .qty(Integer.parseInt(fldQty.getText()))
                .size(selectSize.getSelectionModel().getSelectedItem())
                .type(selectType.getSelectionModel().getSelectedItem())
                .supplierId(supplierSelectById.getSelectionModel().getSelectedItem())
                .build();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="INSERT INTO item VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1,item.getItemId());
            pstm.setString(2,item.getDescription());
            pstm.setString(3,String.valueOf(item.getCost()));
            pstm.setString(4, String.valueOf(item.getSalesPrice()));
            pstm.setString(5, String.valueOf(item.getQty()));
            pstm.setString(6, item.getSize());
            pstm.setString(7, item.getType());
            pstm.setString(8, item.getSupplierId());

            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved...!").show();
                clearFiled();
                loadTableItem();
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
    void btnAddQtyAction(ActionEvent event) {
        try {
            int qty = Integer.parseInt(fldQty.getText()) + Integer.parseInt(fldAddQty.getText());
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE item SET qty = ? WHERE itemId = ?;");
            pstm.setInt(1,qty);
            pstm.setString(2,lblid.getText());
            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Item Updated...!").show();
                clearFiled();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Somthing Went Wrong...!").show();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setScene(preScene);
        stage.show();

    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clearFiled();
    }


    @FXML
    void btnSaveAction(ActionEvent event) {
        Item item=Item
                .builder()
                .itemId(lblid.getText())
                .description(fldDescription.getText())
                .salesPrice(Double.parseDouble(fldSellyingPrice.getText()))
                .cost(Double.parseDouble(fldBuyPrice.getText()))
                .type(selectType.getValue())
                .size(selectSize.getValue())
                .supplierId(supplierSelectById.getValue())
                .build();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE item SET description=?, cost=?, salesPrice=? , size=? , type=? , supplierId=? WHERE itemId=?");
            pstm.setString(1,item.getDescription());
            pstm.setDouble(2,item.getCost());
            pstm.setDouble(3,item.getSalesPrice());
            pstm.setString(4,item.getSize());
            pstm.setString(5,item.getType());
            pstm.setString(6,item.getSupplierId());
            pstm.setString(7,item.getItemId());
            if(pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated...!").show();
                    clearFiled();
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

    public void btnPlaceOrderAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        colBuyingPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("cost"));
        colSellyingPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("salesPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colSize.setCellValueFactory(new TreeItemPropertyValueFactory<>("size"));
        colType.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        colSupplierId.setCellValueFactory(new TreeItemPropertyValueFactory<>("supplierId"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        selectType.setItems(FXCollections.observableArrayList("Kid","Gents","Women"));
        selectSize.setItems(FXCollections.observableArrayList("S","M","L","XL"));

        clearFiled();
        loadSuppliersToComboBox();
        validateIntegerFiled();

        itemTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue!= null){
                setDate(newValue);
            }
        } );

        fldSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                itemTable.setPredicate(new Predicate<TreeItem<ItemTm>>() {
                    @Override
                    public boolean test(TreeItem<ItemTm> itemTmTreeItem) {
                        boolean flag = itemTmTreeItem.getValue().getItemId().contains(newValue) || itemTmTreeItem.getValue().getDescription().contains(newValue);
                        return flag;
                    }
                });
            }
        });

        supplierSelectById.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index=supplierSelectById.getSelectionModel().getSelectedIndex();
                supplierSelectByName.getSelectionModel().select(index);
            }
        });

        supplierSelectByName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index=supplierSelectByName.getSelectionModel().getSelectedIndex();
                supplierSelectById.getSelectionModel().select(index);
            }
        });

    }

    private void setDate(TreeItem<ItemTm> newValue) {
        btnAdd.setDisable(true);
        btnAddQty.setDisable(false);
        fldQty.setDisable(true);
        fldAddQty.setDisable(false);
        lblid.setText(newValue.getValue().getItemId());
        fldDescription.setText(newValue.getValue().getDescription());

        int index = supplierSelectById.getItems().indexOf(newValue.getValue().getSupplierId());
        supplierSelectById.setValue(supplierSelectById.getItems().get(index));
        supplierSelectByName.setValue(supplierSelectByName.getItems().get(index));

        fldBuyPrice.setText(String.valueOf(newValue.getValue().getCost()));
        fldSellyingPrice.setText(String.valueOf(newValue.getValue().getSalesPrice()));
        selectType.setValue(newValue.getValue().getType());
        selectSize.setValue(newValue.getValue().getSize());
        fldQty.setText(String.valueOf(newValue.getValue().getQty()));
    }

    private void loadTableItem() {
        ObservableList<ItemTm> tmList= FXCollections.observableArrayList();
        try {
            List<Item> list=new ArrayList<>();
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                list.add(Item.builder()
                        .itemId(resultSet.getString(1))
                        .description(resultSet.getString(2))
                        .cost(Double.parseDouble(resultSet.getString(3)))
                        .salesPrice(Double.parseDouble(resultSet.getString(4)))
                        .qty(Integer.parseInt(resultSet.getString(5)))
                        .size(resultSet.getString(6))
                        .type(resultSet.getString(7))
                        .supplierId(resultSet.getString(8))
                        .build());
            }
            for (Item item:list){
                JFXButton button=new JFXButton("DELETE");
                button.setStyle("-fx-pref-width: 100; -fx-pref-height: 30; -fx-background-color: red; -fx-border-radius: 15; -fx-font-size: 15px; -fx-font-weight: 800; -fx-text-fill: black; -fx-border-color: black; -fx-background-radius: 15; -fx-border-width: 2px;" );
                button.setOnAction(actionEvent -> {
                    try {
                        PreparedStatement pstm1= connection.prepareStatement("DELETE FROM item WHERE itemId=?");
                        pstm1.setString(1,item.getItemId());
                        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete " + item.getSupplierId() + " Item", ButtonType.YES, ButtonType.NO).showAndWait();
                        if (buttonType.get() == ButtonType.YES){
                            if (pstm1.executeUpdate()>0){
                                new Alert(Alert.AlertType.INFORMATION,"This \""+item.getSupplierId()+"\" Item Deleted..!").show();
                                loadTableItem();
                                generateId();
                            }
                            else {
                                new Alert(Alert.AlertType.INFORMATION,"Something Went Wrong..!").show();
                                loadTableItem();
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                tmList.add(ItemTm.builder()
                        .itemId(item.getItemId())
                        .description(item.getDescription())
                        .cost(item.getCost())
                        .salesPrice(item.getSalesPrice())
                        .qty(item.getQty())
                        .size(item.getSize())
                        .type(item.getType())
                        .supplierId(item.getSupplierId())
                        .btn(button)
                        .build());
            }
            TreeItem<ItemTm> treeI = new RecursiveTreeItem<ItemTm>(tmList, RecursiveTreeObject::getChildren);
            itemTable.setRoot(treeI);
            itemTable.setShowRoot(false);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFiled() {
        btnAdd.setDisable(false);
        btnAddQty.setDisable(true);
        fldQty.setDisable(false);
        fldAddQty.setDisable(true);
        loadTableItem();
        generateId();
        fldAddQty.clear();
        fldDescription.clear();
        fldQty.clear();
        fldBuyPrice.clear();
        fldSellyingPrice.clear();
        supplierSelectById.getSelectionModel().clearSelection();
        supplierSelectByName.getSelectionModel().clearSelection();
        selectSize.getSelectionModel().clearSelection();
        selectType.getSelectionModel().clearSelection();
    }

    private void validateIntegerFiled() {
        fldQty.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        fldBuyPrice.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newText = change.getControlNewText();
                if (newText.isEmpty()) {
                    return change;
                } else if (newText.matches("-?(([0-9]+(\\.[0-9]*)?)|\\.[0-9]+)")) {
                    return change;
                } else {
                    return null; // Reject the change if it doesn't match the pattern
                }
            };

            TextFormatter<String> textFormatter = new TextFormatter<>(filter);
            fldBuyPrice.setTextFormatter(textFormatter);
        });

        fldSellyingPrice.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newText = change.getControlNewText();
                if (newText.isEmpty()) {
                    return change;
                } else if (newText.matches("-?(([0-9]+(\\.[0-9]*)?)|\\.[0-9]+)")) {
                    return change;
                } else {
                    return null; // Reject the change if it doesn't match the pattern
                }
            };

            TextFormatter<String> textFormatter = new TextFormatter<>(filter);
            fldSellyingPrice.setTextFormatter(textFormatter);
        });

        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (!change.getControlNewText().matches("-?\\d*")) {
                return null;
            }
            return change;
        });
        fldAddQty.setTextFormatter(textFormatter);
    }


    private void loadSuppliersToComboBox() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT supplierId , supplierName FROM supplier;");
            ResultSet resultSet = pstm.executeQuery();

            ObservableList<String> listId = FXCollections.observableArrayList();
            ObservableList<String> listName = FXCollections.observableArrayList();
            while (resultSet.next()) {
                listId.add(resultSet.getString(1));
                listName.add(resultSet.getString(2));
            }
            supplierSelectById.setItems(listId);
            supplierSelectByName.setItems(listName);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT itemid FROM item ORDER BY supplierId DESC LIMIT 1;");
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                int num = Integer.parseInt(resultSet.getString(1).split("[I]")[1]);
                num++;
                lblid.setText(String.format("I%03d", num));
            } else {
                lblid.setText("I001");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
    }
}
