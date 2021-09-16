package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminDashBordFormController {
    public AnchorPane root;
    public JFXButton btnManageItem;
    public JFXButton btnSystemReports;
    public JFXButton btnManageProduct;
    public JFXButton btnManageCashiers;
    public Label lblCashiersCount;
    public Label lblPendingOrderCount;
    public ImageView bntBack;
    public JFXButton btnCustomer;

    public void initialize(){
        countActiveUses ();
        getAllPendingOrderCount();
    }

    public void setUI(String location){
        try {
            this.root.getChildren ().clear ();
            this.root.getChildren ().add ( FXMLLoader.load ( getClass ().getResource ( "../view/"+location ) ));
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void countActiveUses(){
        try {
            List< User > allActiveUsers = new UserController ( ).getAllActiveUsers ( );

            lblCashiersCount.setText ( String.valueOf ( allActiveUsers.size () ) );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    private void getAllPendingOrderCount(){
        int size = CashierFormController.tempTableArray.size( );
        System.out.println(size );
        lblPendingOrderCount.setText( String.valueOf( size ) );
    }

    public void btnManageItemOnAction ( ActionEvent actionEvent ) {
        setUI ( "ManageItemForm.fxml" );
    }

    public void btnSystemReportsOnAction ( ActionEvent actionEvent ) {
        setUI ( "SystemReportForm.fxml" );
    }

    public void btnManageProductOnAction ( ActionEvent actionEvent ) {
        setUI ( "ManageProductFrom.fxml" );
    }

    public void btnManageCashiersOnAction ( ActionEvent actionEvent ) {
        setUI ( "ManageUserForm.fxml" );
    }

    public void bntBackOnAction ( MouseEvent mouseEvent ) {
        Stage stage = (Stage) bntBack.getScene ().getWindow ();
        stage.close ();

        try {
            Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/MainDashBoardForm.fxml" ) ) );
            Stage stage1 = new Stage ( );
            stage1.setScene ( scene );
            stage1.show ();

        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnCustomerOnAction ( ActionEvent actionEvent ) {
        setUI( "ManageCustomerForm.fxml" );
    }
}
