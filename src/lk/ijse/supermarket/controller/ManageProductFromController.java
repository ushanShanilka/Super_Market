package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.supermarket.model.Item;
import lk.ijse.supermarket.model.Product;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ManageProductFromController {
    public AnchorPane root;
    public JFXTextField txtProductId;
    public JFXTextField txtProductDescription;
    public JFXTextField txtProductName;
    public JFXTextField txtSpec;
    public JFXTextField txtDisplayName;
    public JFXTextField txtBrands;
    public JFXCheckBox checkAvailability;
    public JFXCheckBox checkState;
    public JFXButton btnSave;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public TableView tblManageProduct;
    public TableColumn colProductId;
    public TableColumn colName;
    public TableColumn colDescription;
    public TableColumn colSpec;
    public TableColumn colDisplayName;
    public TableColumn colAvailability;
    public TableColumn colActiveState;
    public TableColumn colBrands;
    public TableColumn colOption;

    public void initialize(){
        colProductId.setCellValueFactory ( new PropertyValueFactory<> ( "productId" ) );
        colName.setCellValueFactory ( new PropertyValueFactory<> ( "name" ) );
        colDescription.setCellValueFactory ( new PropertyValueFactory<> ( "description" ) );
        colSpec.setCellValueFactory ( new PropertyValueFactory<> ( "spec" ) );
        colDisplayName.setCellValueFactory ( new PropertyValueFactory<> ( "displayName" ) );
        colAvailability.setCellValueFactory ( new PropertyValueFactory<> ( "availability" ) );
        colActiveState.setCellValueFactory ( new PropertyValueFactory<> ( "activeState" ) );
        colBrands.setCellValueFactory ( new PropertyValueFactory<> ( "brands" ) );
        colOption.setCellValueFactory ( new PropertyValueFactory<> ( "btn" ) );
        colOption.setStyle("-fx-alignment:center");

        getAllProduct();
        generateId();

        tblManageProduct.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( ( ProductTM ) newValue );
                });
    }

    public void setData (ProductTM tm){
        try {
            txtProductId.setText(tm.getProductId ());
            txtProductName.setText(tm.getName());
            txtProductDescription.setText(tm.getDescription ());
            txtSpec.setText(tm.getSpec ());
            txtDisplayName.setText(tm.getDisplayName ());
            checkAvailability.setSelected (tm.isAvailability ());
            checkState.setSelected (tm.isActiveState ());
            txtBrands.setText ( tm.getBrands () );
        } catch (NullPointerException ex) {

        }
    }

    public void getAllProduct(){
        try {
            List< Product > all = new ProductController ( ).getAll ( );

            ObservableList< ProductTM > productTMS = FXCollections.observableArrayList ( );


            for ( Product product:all) {
                JFXButton delete = new JFXButton ( "DELETE" );
                productTMS.add ( new ProductTM (
                        product.getProductId (),product.getName (),
                        product.getDescription (),product.getSpec (),
                        product.getDisplayName (),product.isAvailability (),
                        product.isActiveState (),product.getBrands (),delete
                ) );
                delete.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                delete.setOnAction ( (e) ->{
                    try {
                        boolean b = new ProductController ( ).deleteProduct ( product.getProductId () );
                        if (b){
                            new Alert ( Alert.AlertType.CONFIRMATION,"Product Delete!" ).show ();
                            getAllProduct ();
                        }else {
                            new Alert ( Alert.AlertType.WARNING,"Delete Fail!" ).show ();
                        }
                    } catch ( SQLException throwables ) {
                        throwables.printStackTrace ( );
                    } catch ( ClassNotFoundException notFoundException ) {
                        notFoundException.printStackTrace ( );
                    }
                } );
            }
            tblManageProduct.setItems ( productTMS );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnSaveOnAction ( ActionEvent actionEvent ) {
        if (Pattern.compile( "^[A-z]{1,20}( )[A-z]{1,10}" ).matcher( txtProductName.getText( ) ).matches( )) {
            if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtProductDescription.getText( ) ).matches( )) {
                if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtSpec.getText( ) ).matches( )) {
                    if (Pattern.compile( "^[A-z]{1,20}( )[A-z]{1,20}" ).matcher( txtDisplayName.getText( ) ).matches( )) {
                        if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtBrands.getText( ) ).matches( )) {
                            Product product = new Product(
                                    txtProductId.getText( ) ,
                                    txtProductName.getText( ) ,
                                    txtProductDescription.getText( ) ,
                                    txtSpec.getText( ) ,
                                    txtDisplayName.getText( ) ,
                                    checkAvailability.isSelected( ) ,
                                    checkState.isSelected( ) ,
                                    txtBrands.getText( )
                            );
                            try {
                                if (new ProductController( ).saveProduct( product )) {
                                    new Alert( Alert.AlertType.CONFIRMATION , "Saved @!" ).show( );
                                    getAllProduct( );
                                    generateId( );
                                }
                                else {
                                    new Alert( Alert.AlertType.ERROR , "Failed @!" ).show( );
                                }
                            } catch ( SQLException throwables ) {
                                throwables.printStackTrace( );
                            } catch ( ClassNotFoundException e ) {
                                e.printStackTrace( );
                            }
                        }else {
                            txtBrands.setFocusColor( Paint.valueOf( "red" ) );
                            txtBrands.requestFocus();
                        }
                    }else {
                        txtDisplayName.setFocusColor( Paint.valueOf( "red" ) );
                        txtDisplayName.requestFocus();
                    }
                }else {
                    txtSpec.setFocusColor( Paint.valueOf( "red" ) );
                    txtSpec.requestFocus();
                }
            }else {
                txtProductDescription.setFocusColor( Paint.valueOf( "red" ) );
                txtProductDescription.requestFocus();
            }
        }else {
            txtProductName.setFocusColor( Paint.valueOf( "red" ) );
            txtProductName.requestFocus();
        }
    }

    public void btnClearOnAction ( ActionEvent actionEvent ) {
        txtProductName.setText( "" );
        txtProductDescription.setText( "" );
        txtSpec.setText( "" );
        txtDisplayName.setText( "" );
        txtBrands.setText( "" );
    }

    public void btnUpdateOnAction ( ActionEvent actionEvent ) {
        Product product = new Product (
                txtProductId.getText ( ) ,
                txtProductName.getText ( ) ,
                txtProductDescription.getText ( ) ,
                txtSpec.getText ( ) ,
                txtDisplayName.getText ( ) ,
                checkAvailability.isSelected ( ) ,
                checkState.isSelected ( ) ,
                txtBrands.getText ( )
        );
        try {
            if (new ProductController ().updateProduct ( product )){
                new Alert ( Alert.AlertType.CONFIRMATION,"Update !" ).show ();
                getAllProduct ();
            }else {
                new Alert ( Alert.AlertType.WARNING,"Fail !" ).show ();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void searchProductOnAction ( ActionEvent actionEvent ) {
        try {
            Product product = new ProductController ( ).searchProduct ( txtProductId.getText ( ) );
            if (product!=null){
                txtProductName.setText ( product.getName () );
                txtProductDescription.setText ( product.getDescription () );
                txtSpec.setText ( product.getSpec () );
                txtDisplayName.setText ( product.getDisplayName () );
                checkAvailability.setSelected ( product.isAvailability () );
                checkState.setSelected ( product.isActiveState () );
                txtBrands.setText ( product.getBrands () );
            }else {
                new Alert ( Alert.AlertType.ERROR,"Empty" ).show ();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void generateId(){
        try {
            ResultSet resultSet = new ProductController( ).autoGenerateID( );
            if (resultSet.next()){
                String oldId = resultSet.getString( 1 );
                String substring = oldId.substring( 1 , 4 );
                int intId = Integer.parseInt( substring );

                intId = intId + 1;
                if (intId<10){
                    txtProductId.setText( "P00"+intId );
                }else if (intId<100){
                    txtProductId.setText( "P0"+intId );
                }else if (intId<1000){
                    txtProductId.setText( "P"+intId );
                }
            }else {
                txtProductId.setText( "P001" );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

}
