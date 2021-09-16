package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.supermarket.model.*;
import lk.ijse.supermarket.view.tm.CustomerTM;
import lk.ijse.supermarket.view.tm.TempOrderTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
    public JFXButton btnAdd;
    public Label lblTotal;
    public Label lblCashierId;
    public TableColumn colOption;
    public JFXButton btnPlaceOrder;
    public JFXButton btnNew;

    static ArrayList< TempData > temps = new ArrayList<>( );

    static ArrayList< TempTable > tempTableArray = new ArrayList<>( );

    public void initialize(){
        colPropertyId.setCellValueFactory( new PropertyValueFactory<>( "propertyId" ) );
        colProductName.setCellValueFactory( new PropertyValueFactory<>( "productName" ) );
        colUnitPrice.setCellValueFactory( new PropertyValueFactory<>( "unitPrice" ) );
        colQty.setCellValueFactory( new PropertyValueFactory<>( "qty" ) );
        colDiscount.setCellValueFactory( new PropertyValueFactory<>( "discount" ) );
        colTotal.setCellValueFactory( new PropertyValueFactory<>( "total" ) );
        colOption.setCellValueFactory( new PropertyValueFactory<>( "btn" ) );

        getAllPropertyId();
        generateDateTime();
        setOrderId();
        setCustomerId();
        lblCashierId.setText(  CashierLoginFormController.userId );
        setBtnValid(true);
    }

    public void btnAddOnAction ( ActionEvent actionEvent ) {
        if (Pattern.compile( "^(C)[0-9]{1,5}" ).matcher( txtCusId.getText( ) ).matches( )) {
            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCusType.getText( ) ).matches( )) {
                if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtCusName.getText( ) ).matches( )) {
                    if (Pattern.compile( "^[A-z]{1,20}(, )[A-z]{1,20}" ).matcher( txtCusAddress.getText( ) ).matches( )) {
                        if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtCusCity.getText( ) ).matches( )) {
                            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCusProvince.getText( ) ).matches( )) {
                                if (Pattern.compile( "^[0-9]{9,10}" ).matcher( txtCusContact.getText( ) ).matches( )) {
                                    if (Pattern.compile( "^[A-z]{1,20}( )[A-z]{1,20}" ).matcher( txtProductName.getText( ) ).matches( )) {
                                        if (Pattern.compile( "^[0-9]{1,10}" ).matcher( txtOrderQty.getText( ) ).matches( )) {
                                            String date = lblDate.getText( );
                                            String cashierId = CashierLoginFormController.userId;

                                            int qty = Integer.parseInt( txtOrderQty.getText( ) );
                                            double uniPrice = Double.parseDouble( txtUnitPrice.getText( ) );
                                            double dic = Double.parseDouble( txtDiscount.getText( ) );

                                            double dicTot = (dic * qty);
                                            double subTot = (uniPrice * qty) - (dic * qty);

                                            TempData tempData = new TempData(
                                                    txtOrderId.getText( ) ,
                                                    date ,
                                                    txtCusId.getText( ) ,
                                                    txtCusType.getText( ) ,
                                                    txtCusName.getText( ) ,
                                                    txtCusAddress.getText( ) ,
                                                    txtCusCity.getText( ) ,
                                                    txtCusProvince.getText( ) ,
                                                    Integer.parseInt( txtCusContact.getText( ) ) ,
                                                    cashierId ,
                                                    subTot
                                            );

                                            int rowNumber1 = isExistsTempData( tempData );

                                            if (rowNumber1==-1) {
                                                temps.add( tempData );
                                                tblTempOrder.refresh( );
                                            }
                                            else {
                                                tblTempOrder.refresh( );
                                            }

                                            TempTable tempTable = new TempTable(
                                                    txtOrderId.getText( ) ,
                                                    String.valueOf( cmbSelectPropertyId.getValue( ) ) ,
                                                    txtProductName.getText( ) ,
                                                    Double.parseDouble( txtUnitPrice.getText( ) ) ,
                                                    Integer.parseInt( txtOrderQty.getText( ) ) ,
                                                    Double.parseDouble( String.valueOf( dicTot ) ) ,
                                                    Double.parseDouble( String.valueOf( subTot ) )
                                            );

                                            int rowNumber = isExists( tempTable );
                                            if (rowNumber==-1) {
                                                if (Integer.parseInt( txtQty.getText( ) ) >= Integer.parseInt( txtOrderQty.getText( ) )) {
                                                    tempTableArray.add( tempTable );
                                                    getAllProcessingOrder( );
                                                }
                                                else {
                                                    new Alert( Alert.AlertType.WARNING , "Out of Bounds" ).show( );
                                                }
                                            }
                                            else {
                                                if (Integer.parseInt( txtQty.getText( ) ) >= tempTableArray.get( rowNumber ).getQty( ) + Integer.parseInt( txtOrderQty.getText( ) )) {
                                                    tempTableArray.get( rowNumber ).setQty( tempTableArray.get( rowNumber ).getQty( ) + Integer.parseInt( txtOrderQty.getText( ) ) );
                                                    tempTableArray.get( rowNumber ).setSubTotal( tempTableArray.get( rowNumber ).getSubTotal( ) + subTot );
                                                    tempTableArray.get( rowNumber ).setDiscount( tempTableArray.get( rowNumber ).getDiscount( ) + dicTot );
                                                    tblTempOrder.refresh( );
                                                }else {
                                                    new Alert( Alert.AlertType.WARNING , "Out of Bounds" ).show( );
                                                }
                                            }
                                            getAllProcessingOrder( );
                                            generateTotal( );
                                            txtOrderQty.setText("");
                                            setBtnValid(false);
                                        }else {
                                            txtOrderQty.setFocusColor( Paint.valueOf( "red" ) );
                                            txtOrderQty.requestFocus( );
                                        }
                                    }else {
                                        txtProductName.setFocusColor( Paint.valueOf( "red" ) );
                                        txtProductName.requestFocus( );
                                    }
                                }else {
                                    txtCusContact.setFocusColor( Paint.valueOf( "red" ) );
                                    txtCusContact.requestFocus( );
                                }
                            }else {
                                txtCusProvince.setFocusColor( Paint.valueOf( "red" ) );
                                txtCusProvince.requestFocus( );
                            }
                        }else {
                            txtCusCity.setFocusColor( Paint.valueOf( "red" ) );
                            txtCusCity.requestFocus( );
                        }
                    }else {
                        txtCusAddress.setFocusColor( Paint.valueOf( "red" ) );
                        txtCusAddress.requestFocus( );
                    }
                }else {
                    txtCusName.setFocusColor( Paint.valueOf( "red" ) );
                    txtCusName.requestFocus( );
                }
            }else {
                txtCusType.setFocusColor( Paint.valueOf( "red" ) );
                txtCusType.requestFocus( );
            }
        }else {
            txtCusId.setFocusColor( Paint.valueOf( "red" ) );
            txtCusId.requestFocus( );
        }
    }

    public void btnConfirmOnAction ( ActionEvent actionEvent ) {
        try{
            int tempId = Integer.parseInt( txtOrderId.getText().split( "O" )[ 1 ] );
            tempId+=1;
            if (tempId < 10){
                txtOrderId.setText("O00"+tempId);
            }else if (tempId<100){
                txtOrderId.setText("O0"+tempId);
            }

            getAllOrderIdFromArray();
            getAllPropertyId();
            clear();
            tblTempOrder.setItems( null );
            setBtnValid(true);
        }catch ( NullPointerException e ){

        }
    }

    public void cmbSelectPropertyId ( ActionEvent actionEvent ) {
        try {
            List< Item > allItems = new ItemController( ).getAllActiveStateItems( );

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
        }catch ( NullPointerException e ){

        }
    }

    public void click ( MouseEvent mouseEvent ) {
        for ( TempData data:temps ) {
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                lblCashierId.setText(data.getCashierId() );
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
            JFXButton btn = new JFXButton( "Delete" );
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),data.getUnitPrice(),data.getQty(),data.getDiscount(),data.getSubTotal(),btn ) );
            }
            btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
            btn.setOnAction( (e)->{
                for ( int i = 0; i < tempTableArray.size( ); i++ ) {
                    TempTable table = tempTableArray.get( i );
                    if (txtOrderId.getText( ).equals( table.getOrderId( ) )){
                        if (data.getPropertyId( ).equals( table.getPropertyId( ) )){
                            boolean remove = tempTableArray.remove( table );
                            if (remove){
                                getAllProcessingOrder();
                            }
                        }
                    }
                }
            } );
            lblTotal.setText( String.valueOf( data.getSubTotal() ) );
        }
        tblTempOrder.setItems( list );
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

    public void btnPlaceOrderOnAction ( ActionEvent actionEvent ) {
        Customer customer = new Customer(
                txtCusId.getText( ) ,
                txtCusType.getText( ) ,
                txtCusName.getText( ) ,
                txtCusAddress.getText( ) ,
                txtCusCity.getText( ) ,
                txtCusProvince.getText( ) ,
                Integer.parseInt( txtCusContact.getText( ) )
        );
        ArrayList< Order > orders = new ArrayList<>( );
        Order order = new Order( txtOrderId.getText( ) , lblDate.getText(), Double.parseDouble( lblTotal.getText( ) ) , txtCusId.getText( ) , Integer.parseInt( lblCashierId.getText( ) ) );
        orders.add(order);
        customer.setOrders(orders);

        ArrayList< OrderDetail > details = new ArrayList<>( );

        for ( TempTable tm : tempTableArray) {
            if (txtOrderId.getText().equals( tm.getOrderId() )){
                details.add( new OrderDetail( tm.getQty( ) , tm.getUnitPrice( ) , txtOrderId.getText() , tm.getPropertyId( ) ) );
            }
        }
        order.setDetails( details );

        try {
            if (new CashierController().saveCustomer( customer )){
                deleteTempData();
                new Alert( Alert.AlertType.CONFIRMATION,"Success ! " ).show();
                getAllOrderIdFromArray();
                getAllProcessingOrder();
                clear();
                tblTempOrder.setItems(null);
            }else {
                new Alert( Alert.AlertType.WARNING,"Fail !" ).show();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        }
    }

    public void btnCancelOnAction ( ActionEvent actionEvent ) {
        deleteTempData();
        getAllOrderIdFromArray();
        getAllProcessingOrder();
        clear();
    }

    public void btnNewOnAction ( ActionEvent actionEvent ) {
        clear();
        setOrderId();
        setBtnValid(true);
        txtCusType.requestFocus();
    }

    public void searchCustomerOnAction ( ActionEvent actionEvent ) {
        try {
            Customer customer = new CustomerController( ).getCustomer( txtCusId.getText( ) );
            if (customer!=null){
                txtCusType.setText( customer.getCusType() );
                txtCusName.setText( customer.getCusName() );
                txtCusAddress.setText( customer.getCusAddress() );
                txtCusCity.setText( customer.getCusCity() );
                txtCusProvince.setText( customer.getCusProvince() );
                txtCusContact.setText( String.valueOf( customer.getCusContact() ) );
            }else {
                new Alert( Alert.AlertType.ERROR,"Empty Customer !" ).show();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    private void setOrderId(){
        try {
            String s = new CashierController( ).generateOrderId( );
            txtOrderId.setText( s );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }

    }

    private void setCustomerId(){
        try {
            String s = new CustomerController().generateCustomerId( );
            txtCusId.setText( s );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
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

    private void deleteTempData(){
        for ( int i = 0; i < temps.size( ); i++ ) {
            TempData data = temps.get( i );
            if (txtOrderId.getText().equals( data.getOrderId() )){
                boolean remove = temps.remove( data );
                if (remove){
                    for ( int j = 0; j < tempTableArray.size( ); j++ ) {
                        TempTable table = tempTableArray.get( j );
                        if (txtOrderId.getText().equals( table.getOrderId() )){
                            tempTableArray.remove( table );
                        }
                    }
                    list.refresh();
                    tblTempOrder.refresh();
                }
            }
        }
    }

    private int isExistsTempData ( TempData tempData ) {
        for ( int i = 0; i < temps.size( ); i++ ) {
            if (temps.get( i ).getOrderId().equals( tempData.getOrderId() )){
                return i;
            }
        }
        return -1;
    }

    private int isExists ( TempTable tempTable ) {
        for ( int i = 0; i < tempTableArray.size( ); i++ ) {
            if (tempTableArray.get( i ).getOrderId().equals( txtOrderId.getText() ) && tempTableArray.get( i ).getPropertyId().equals( tempTable.getPropertyId() )){
                return i;
            }
        }
        return -1;
    }

    double totalCost = 0.0;
    int qty = 0;
    public void generateTotal(){
        qty = Integer.parseInt( txtOrderQty.getText( ) );
        double uniPrice = Double.parseDouble(txtUnitPrice.getText( ));
        double dic = Double.parseDouble( txtDiscount.getText( ) );

        double temp = ((uniPrice * qty) - (dic * qty));
        totalCost+=temp;

        lblTotal.setText( String.valueOf( totalCost ) );
    }

    public void getAllProcessingOrder(){
        tblTempOrder.setItems( null );
        ObservableList< TempOrderTM > list = FXCollections.observableArrayList( );

        for ( TempTable data:tempTableArray) {
            JFXButton btn = new JFXButton( "Delete" );
            if (txtOrderId.getText().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),
                                           data.getUnitPrice(),data.getQty(),
                                           data.getDiscount(),data.getSubTotal(),btn
                ) );
            }
            btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
            btn.setOnAction( (e)->{
                for ( int i = 0; i < tempTableArray.size( ); i++ ) {
                    TempTable table = tempTableArray.get( i );
                    if (txtOrderId.getText( ).equals( table.getOrderId( ) )){
                        if (data.getPropertyId( ).equals( table.getPropertyId( ) )){
                            boolean remove = tempTableArray.remove( table );
                            if (remove){
                                getAllProcessingOrder();
                            }
                        }
                    }
                }
            } );
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

    public void getAllPropertyId(){
        try {
            List< Item > all = new ItemController( ).getAllActiveStateItems( );
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

    private void setBtnValid(boolean x){
        btnPlaceOrder.setDisable(x);
        btnConfirm.setDisable(x);
        btnCancel.setDisable(x);
    }

    private void clear(){
        setCustomerId();
        txtCusType.setText( "" );
        txtCusName.setText( "" );
        txtCusAddress.setText( "" );
        txtCusCity.setText( "" );
        txtCusProvince.setText( "" );
        txtCusContact.setText( "" );
        txtProductName.setText( "" );
        txtUnitPrice.setText( "" );
        txtQty.setText( "" );
        txtDiscount.setText( "" );
        txtOrderQty.setText( "" );
        lblTotal.setText( "" );
    }

}
