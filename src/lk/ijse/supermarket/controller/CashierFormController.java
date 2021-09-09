package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.supermarket.model.Item;
import lk.ijse.supermarket.model.Product;
import lk.ijse.supermarket.model.TempData;
import lk.ijse.supermarket.model.TempTable;
import lk.ijse.supermarket.view.tm.TempOrderTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CashierFormController {
    public JFXListView list;
    public JFXButton btnCancel;
    public JFXButton btnConfirm;
    public JFXComboBox cmbSelectPropertyId;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtCusId;
    public JFXTextField txtCusType;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtCusProvince;
    public JFXTextField txtCusContact;
    public JFXTextField txtOrderId;
    public Label lblCashierName;
    public JFXTextField txtProductName;
    public JFXTextField txtOrderQty;
    public TableView tblTempOrder;
    public TableColumn colPropertyId;
    public TableColumn colProductName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public ImageView btnBack;

    ArrayList< TempData > temps = new ArrayList<>( );

    ArrayList< TempTable > tempTableArray = new ArrayList<>( );


    public void initialize(){
        colPropertyId.setCellValueFactory( new PropertyValueFactory<>( "propertyId" ) );
        colProductName.setCellValueFactory( new PropertyValueFactory<>( "productName" ) );
        colUnitPrice.setCellValueFactory( new PropertyValueFactory<>( "unitPrice" ) );
        colQty.setCellValueFactory( new PropertyValueFactory<>( "qty" ) );
        colDiscount.setCellValueFactory( new PropertyValueFactory<>( "discount" ) );
        colTotal.setCellValueFactory( new PropertyValueFactory<>( "total" ) );

        getAllPropertyId();
        generateDateTime();
        lblCashierName.setText( CashierLoginFormController.userName );
    }

    public void generateDateTime() {
        lblDate.setText( LocalDate.now().toString());

        Timeline timeline = new Timeline( new KeyFrame( Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "hh:mm:ss a");
            lblTime.setText( LocalDateTime.now().format( formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount( Animation.INDEFINITE);
        timeline.play();
    }

    public void btnCancelOnAction ( ActionEvent actionEvent ) {
    }

    public void btnConfirmOnAction ( ActionEvent actionEvent ) {
        String date = lblDate.getText( );
        String time = lblTime.getText( );
        String dateAndTime = (date+"/"+time);

        String nameText = CashierLoginFormController.userName;

        TempData tempData = new TempData(
                txtOrderId.getText(),
                dateAndTime,
                txtCusId.getText(),
                txtCusType.getText(),
                txtCusName.getText(),
                txtCusAddress.getText(),
                txtCusCity.getText(),
                txtCusProvince.getText(),
                Integer.parseInt( txtCusContact.getText() ),
                nameText
        );
        boolean add = temps.add( tempData );
        if (add){
            TempTable tempTable = new TempTable(
                    txtOrderId.getText(),
                    String.valueOf( cmbSelectPropertyId.getValue( ) ) ,
                    txtProductName.getText( ) ,
                    BigDecimal.valueOf ( Double.parseDouble ( txtUnitPrice.getText ( ) ) ) ,
                    Integer.parseInt( txtOrderQty.getText( ) ) ,
                    BigDecimal.valueOf( Double.parseDouble( txtDiscount.getText( ) ) ) ,
                    BigDecimal.valueOf( Double.parseDouble( txtUnitPrice.getText( ) ) )
            );
            tempTableArray.add( tempTable );
        }
        getAllOrderIdFromArray();
        getAllProcessingOrder();
    }

    public void getAllProcessingOrder(){
        tblTempOrder.setItems( null );
        ObservableList< TempOrderTM > list = FXCollections.observableArrayList( );
        for ( TempTable data:tempTableArray) {
            if (txtOrderId.getText().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),data.getUnitPrice(),data.getQty(),data.getDiscount(),data.getTotal() ) );
            }
        }
        tblTempOrder.setItems( list );
    }

    public void getAllOrderIdFromArray(){
        ObservableList< String > obs = FXCollections.observableArrayList( );
        for ( TempData data:temps ) {
            obs.add( data.getOrderId() );
        }
        list.setItems( obs );
    }

    public void click ( MouseEvent mouseEvent ) {
        for ( TempData data:temps ) {
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                lblCashierName.setText( data.getCashierName() );
                txtOrderId.setText( data.getOrderId() );
                txtCusId.setText( data.getCusId() );
                txtCusType.setText( data.getCusType() );
                txtCusName.setText(data.getCusName());
                txtCusAddress.setText(data.getCusAddress());
                txtCusCity.setText(data.getCusCity());
                txtCusProvince.setText(data.getCusProvince());
                txtCusContact.setText( String.valueOf( data.getCusContact() ) );
            }
        }
        ObservableList< TempOrderTM > list = FXCollections.observableArrayList( );

        for ( TempTable data:tempTableArray) {
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),data.getUnitPrice(),data.getQty(),data.getDiscount(),data.getTotal() ) );
            }
        }
        tblTempOrder.setItems( list );
    }

    public void cmbSelectPropertyId ( ActionEvent actionEvent ) {
        try {
            List< Item > allItems = new ItemController( ).getAllItems( );

            for ( Item item:allItems) {
                if (cmbSelectPropertyId.getValue().equals( item.getPropertyId() )){
                    txtUnitPrice.setText( String.valueOf( item.getPrice() ) );
                    txtQty.setText( String.valueOf( item.getQty() ) );
                    txtDiscount.setText( String.valueOf( item.getDiscount() ) );
                    Product product = new ProductController( ).searchProduct( String.valueOf( item.getProductId( ) ) );
                    txtProductName.setText( product.getDisplayName() );
                }
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void getAllPropertyId(){
        try {
            List< Item > all = new ItemController( ).getAllItems( );
            ObservableList< String > propertyId = FXCollections.observableArrayList( );

            for ( Item item:all) {
                propertyId.add( item.getPropertyId() );
            }
            cmbSelectPropertyId.setItems( propertyId );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void btnBackOnAction ( MouseEvent mouseEvent ) {
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
}
