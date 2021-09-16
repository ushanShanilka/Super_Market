package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.model.Customer;
import lk.ijse.supermarket.view.tm.CustomerTM;
import lk.ijse.supermarket.view.tm.ItemTM;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ManageCustomerFormController {
    public AnchorPane root;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colContact;
    public JFXTextField txtCusId;
    public JFXTextField txtCusType;
    public JFXTextField txtCusName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtCusContact;

    public void initialize(){
        colId.setCellValueFactory( new PropertyValueFactory<>( "cusId" ) );
        colType.setCellValueFactory( new PropertyValueFactory<>( "cusType" ) );
        colName.setCellValueFactory( new PropertyValueFactory<>( "cusName" ) );
        colAddress.setCellValueFactory( new PropertyValueFactory<>( "cusAddress" ) );
        colCity.setCellValueFactory( new PropertyValueFactory<>( "cusCity" ) );
        colProvince.setCellValueFactory( new PropertyValueFactory<>( "cusProvince" ) );
        colContact.setCellValueFactory( new PropertyValueFactory<>( "cusContact" ) );

        getAllCustomers();
        tblCustomer.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( ( CustomerTM ) newValue );
                });
    }

    private void setData( CustomerTM tm){
        try {
            txtCusId.setText( tm.getCusId() );
            txtCusType.setText( tm.getCusType() );
            txtCusName.setText( tm.getCusName() );
            txtAddress.setText( tm.getCusAddress() );
            txtCity.setText( tm.getCusCity() );
            txtProvince.setText( tm.getCusProvince() );
            txtCusContact.setText( String.valueOf( tm.getCusContact() ) );
        }catch ( NullPointerException e ){

        }
    }

    public void btnClearOnAction ( ActionEvent actionEvent ) {
        txtCusId.setText( "" );
        txtCusType.setText( "" );
        txtCusName.setText( "" );
        txtAddress.setText( "" );
        txtCity.setText( "" );
        txtProvince.setText( "" );
        txtCusContact.setText( "" );
    }

    public void btnUpdateOnAction ( ActionEvent actionEvent ) {
        if (Pattern.compile( "^(C)[0-9]{1,5}" ).matcher( txtCusId.getText( ) ).matches( )) {
            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCusType.getText( ) ).matches( )) {
                if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtCusName.getText( ) ).matches( )) {
                    if (Pattern.compile( "^[A-z]{1,20}(, )[A-z]{1,20}" ).matcher( txtAddress.getText( ) ).matches( )) {
                        if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCity.getText( ) ).matches( )) {
                            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtProvince.getText( ) ).matches( )) {
                                if (Pattern.compile( "^[0-9]{9,10}" ).matcher( txtCusContact.getText( ) ).matches( )) {
                                    Customer customer = new Customer(
                                            txtCusId.getText( ) ,
                                            txtCusType.getText( ) ,
                                            txtCusName.getText( ) ,
                                            txtAddress.getText( ) ,
                                            txtCity.getText( ) ,
                                            txtProvince.getText( ) ,
                                            Integer.parseInt( txtCusContact.getText( ) )
                                    );

                                    try {
                                        boolean b = new CustomerController( ).updateCustomer( customer );
                                        if (b) {
                                            new Alert( Alert.AlertType.CONFIRMATION , "Updated!" ).show( );
                                            getAllCustomers( );
                                        }
                                        else {
                                            new Alert( Alert.AlertType.WARNING , "Fail!" ).show( );
                                        }
                                    } catch ( SQLException throwables ) {
                                        throwables.printStackTrace( );
                                    } catch ( ClassNotFoundException e ) {
                                        e.printStackTrace( );
                                    }
                                }else {
                                    txtCusContact.setFocusColor( Paint.valueOf( "red" ) );
                                    txtCusContact.requestFocus();
                                }
                            }else {
                                txtProvince.setFocusColor( Paint.valueOf( "red" ) );
                                txtProvince.requestFocus();
                            }
                        }else {
                            txtCity.setFocusColor( Paint.valueOf( "red" ) );
                            txtCity.requestFocus();
                        }
                    }else {
                        txtAddress.setFocusColor( Paint.valueOf( "red" ) );
                        txtAddress.requestFocus();
                    }
                }else {
                    txtCusName.setFocusColor( Paint.valueOf( "red" ) );
                    txtCusName.requestFocus();
                }
            }else {
                txtCusType.setFocusColor( Paint.valueOf( "red" ) );
                txtCusType.requestFocus();
            }
        }else {
            txtCusId.setFocusColor( Paint.valueOf( "red" ) );
            txtCusId.requestFocus();
        }
    }

    public void txtCusIdOnAction ( ActionEvent actionEvent ) {
        try {
            Customer customer = new CustomerController( ).getCustomer( txtCusId.getText( ) );
            if (customer!=null){
                txtCusType.setText( customer.getCusType() );
                txtCusName.setText( customer.getCusName() );
                txtAddress.setText( customer.getCusAddress() );
                txtCity.setText( customer.getCusCity() );
                txtProvince.setText( customer.getCusProvince() );
                txtCusContact.setText( String.valueOf( customer.getCusContact() ) );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    private void getAllCustomers(){
        try {
            List< Customer > allCustomers = new CustomerController( ).getAllCustomers( );
            ObservableList< CustomerTM > customerTMS = FXCollections.observableArrayList( );

            for ( Customer customer : allCustomers) {
                customerTMS.add( new CustomerTM(
                        customer.getCusId(),
                        customer.getCusType(),
                        customer.getCusName(),
                        customer.getCusAddress(),
                        customer.getCusCity(),
                        customer.getCusProvince(),
                        customer.getCusContact()
                ) );
            }
            tblCustomer.setItems( customerTMS );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }
}
