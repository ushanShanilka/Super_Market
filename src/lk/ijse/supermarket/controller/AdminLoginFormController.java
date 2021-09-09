package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class AdminLoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnCancel;
    public JFXButton btnLogin;

    public void loginOnAction ( ActionEvent actionEvent ) {
        try {
        String name = txtUserName.getText ( ).trim ( );
        String password = txtPassword.getText ( ).trim ( );
        if ( name.length ( ) > 0 && password.length ( ) > 0 ) {

            if ( name.equalsIgnoreCase ( "Ushan" )
                 && password.equalsIgnoreCase ( "1234" ) ) {
                Stage stage = ( Stage ) btnCancel.getScene ( ).getWindow ( );
                stage.close ( );

                Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/AdminDashBordForm.fxml" ) ) );
                Stage primaryStage = new Stage ( );
                primaryStage.setScene ( scene );
                primaryStage.show ( );

            }
            else {
                new Alert ( Alert.AlertType.WARNING , "Try Again !!!!" ,
                            ButtonType.OK , ButtonType.NO ).show ( );
            }
        }
        else {
            new Alert ( Alert.AlertType.WARNING , "Empty !!!!" ,
                        ButtonType.OK , ButtonType.NO ).show ( );
        }
        } catch ( IOException e ) {
            e.printStackTrace ( );
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

    public void txtPwKeyReleasedOnAction ( KeyEvent keyEvent ) {
        if (Pattern.compile("^[0-9]{1,4}$").matcher(txtPassword.getText()).matches()) {
            btnLogin.setDisable ( false );
            txtPassword.setFocusColor(Paint.valueOf("blue"));
        }else {
            txtPassword.setFocusColor( Paint.valueOf( "red"));
            btnLogin.setDisable ( true );
        }
    }

    public void txtUserNameKeyReleasedOnAction ( KeyEvent keyEvent ) {
        if ( Pattern.compile( "^[A-z]{1,5}$").matcher( txtUserName.getText()).matches()) {
            btnLogin.setDisable ( false );
            txtUserName.setFocusColor( Paint.valueOf( "blue"));
        }else {
            txtUserName.setFocusColor(Paint.valueOf("red"));
            btnLogin.setDisable ( true );
        }
    }
}
