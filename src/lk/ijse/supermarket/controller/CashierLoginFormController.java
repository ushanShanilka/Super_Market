package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.supermarket.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class CashierLoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public JFXButton btnCancel;

    public static String userName;

    public void loginOnAction ( ActionEvent actionEvent ) {
        try {
            User allActiveStateUsers = new UserController ( ).getActiveUsers ( txtUserName.getText ( ) , txtPassword.getText ( ) );
            userName=txtUserName.getText();
            if (allActiveStateUsers!=null){
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                System.out.println(userName );

                Scene scene = new Scene( FXMLLoader.load( getClass( ).getResource( "../view/CashierForm.fxml" ) ) );
                Stage ps = new Stage( );
                ps.setScene( scene );
                ps.show();

            }else {
                new Alert( Alert.AlertType.WARNING,"Something went Wrong.Please Contact Admin !  " ).show();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

    public void cancelOnActon ( ActionEvent actionEvent ) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

        try {
            Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/MainDashBoardForm.fxml" ) ) );
            Stage primaryStage = new Stage( );
            primaryStage.setScene( scene );
            primaryStage.show( );
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void txtUserNameKeyReleasedOnAction ( KeyEvent keyEvent ) {
    }

    public void txtPwKeyReleasedOnAction ( KeyEvent keyEvent ) {
    }
}
