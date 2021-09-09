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
import java.sql.SQLException;
import java.util.List;

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

    public void setUI(String location){
        try {
            this.root.getChildren ().clear ();
            this.root.getChildren ().add ( FXMLLoader.load( getClass ().getResource ( "../view/" + location ) ) );
        } catch ( IOException e ) {
            e.printStackTrace ( );
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

    public void btnBackOnAction ( MouseEvent mouseEvent ) {
        setUI ( "AdminDashBordForm.fxml" );
    }

    public void btnSaveOnAction ( ActionEvent actionEvent ) {
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
            if (new ProductController ().saveProduct ( product )){
                new Alert ( Alert.AlertType.CONFIRMATION,"Saved @!" ).show ();
                getAllProduct ();
            }else {
                new Alert ( Alert.AlertType.ERROR,"Failed @!" ).show ();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnClearOnAction ( ActionEvent actionEvent ) {
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

}
