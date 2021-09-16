package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainDashBoardFormController {
    public AnchorPane root;
    public JFXButton btnAdmin;
    public JFXButton btnCashier;

    public void btnAdminOnAction ( ActionEvent actionEvent ) {
        Stage stage = (Stage) btnAdmin.getScene ().getWindow ();
        stage.close ();

        try {
            Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/AdminLoginForm.fxml" ) ) );
            Stage stage1 = new Stage ( );
            stage1.setScene ( scene );
            stage1.show ();

        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnCashierOnAction ( ActionEvent actionEvent ){
        Stage stage = (Stage) btnCashier.getScene ().getWindow ();
        stage.close ();

        try {
            Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/CashierLoginForm.fxml" ) ) );
            Stage stage1 = new Stage ( );
            stage1.setScene ( scene );
            stage1.show ();

        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

}
