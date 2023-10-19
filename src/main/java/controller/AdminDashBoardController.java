package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminDashBoardController implements Initializable {

    public AnchorPane anchorPane;
    @FXML
    private BorderPane dashBoard;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    void bnrEmployersAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/Employer.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        EmployerController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage= (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnCreateUserAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/UserRegistration.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        UserRegistrationController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage= (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnInventoryAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/Item.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        ItemController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage= (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnLogOutAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) dashBoard.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LogingForm.fxml"))));
        stage.show();
    }

    @FXML
    void btnOrderDetailsAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/OrderDetails.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        OrderDetailsController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage = (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnOrdersAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/Oders.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        OdersController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage = (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnSalesReportAction(ActionEvent event) {

    }

    @FXML
    void btnSalesReturnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../../resources/view/SalesReturn.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        SalesReturnController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage = (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnSuppliersAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/Supplier.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        SupplierController nextController=fxmlLoader.getController();
        nextController.setPreScene(dashBoard.getScene());

        Stage stage = (Stage) dashBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manageDateAndTime();

    }

    ///////////////////////////////////////////////////////////////////////

    private void manageDateAndTime() {
        Timeline date = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        ), new KeyFrame(Duration.seconds(1)));
        date.setCycleCount(Animation.INDEFINITE);
        date.play();

        Timeline time = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
}
