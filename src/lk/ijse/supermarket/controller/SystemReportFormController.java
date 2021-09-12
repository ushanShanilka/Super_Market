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
import lk.ijse.supermarket.model.Order;
import lk.ijse.supermarket.model.OrderDetail;
import lk.ijse.supermarket.view.tm.OrderDetailTM;
import lk.ijse.supermarket.view.tm.OrderTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SystemReportFormController {
    public AnchorPane root;
    public JFXButton btnOrder;
    public JFXButton btnOrderDetails;
    public TableView tblOrder;
    public TableColumn colOrderTblOrderId;
    public TableColumn colOrderTblDate;
    public TableColumn colOrderTblTotal;
    public TableColumn colOrderTblCusId;
    public TableColumn colOrderTblUserId;
    public TableView tblOrderDetails;
    public TableColumn colDetailQty;
    public TableColumn colDetailUnitPrice;
    public TableColumn colDetailOrderId;
    public TableColumn colDetailPropId;
    public TableColumn colOrderTblOption;
    public JFXTextField txtOrderId;
    public JFXTextField txtOrderDetails;

    public void initialize(){
        colOrderTblOrderId.setCellValueFactory( new PropertyValueFactory<>( "orderId" ) );
        colOrderTblDate.setCellValueFactory( new PropertyValueFactory<>( "dateTime" ) );
        colOrderTblTotal.setCellValueFactory( new PropertyValueFactory<>( "total" ) );
        colOrderTblCusId.setCellValueFactory( new PropertyValueFactory<>( "cusId" ) );
        colOrderTblUserId.setCellValueFactory( new PropertyValueFactory<>( "userId" ) );
        colOrderTblOption.setCellValueFactory( new PropertyValueFactory<>( "btn" ) );
        colOrderTblOption.setStyle ( "-fx-alignment:center" );
        getAllOrders();

        colDetailQty.setCellValueFactory( new PropertyValueFactory<>( "qty" ) );
        colDetailUnitPrice.setCellValueFactory( new PropertyValueFactory<>( "unitPrice" ) );
        colDetailOrderId.setCellValueFactory( new PropertyValueFactory<>( "orderId" ) );
        colDetailPropId.setCellValueFactory( new PropertyValueFactory<>( "propertyId" ) );
        getAllOrderDetails();
    }

    public void btnOrderOnAction ( ActionEvent actionEvent ) {
        tblOrder.setVisible( true );
        tblOrderDetails.setVisible( false );
        txtOrderId.setVisible( true );
        txtOrderDetails.setVisible( false );
        getAllOrders();
    }

    public void btnOrderDetailsOnAction ( ActionEvent actionEvent ) {
        tblOrder.setVisible( false );
        tblOrderDetails.setVisible( true );
        txtOrderId.setVisible( false );
        txtOrderDetails.setVisible( true );
        getAllOrderDetails();
    }

    private void getAllOrders(){
        try {
            List< Order > allOrders = new SystemReportController( ).getAllOrders( );
            ObservableList< OrderTM > orderTMS = FXCollections.observableArrayList( );

            for ( Order order : allOrders) {
                JFXButton btn = new JFXButton( "DELETE" );
                orderTMS.add( new OrderTM(
                        order.getOrderId(),order.getDateTime(),
                        order.getTotal(),order.getCusId(),
                        order.getUserId(),btn
                ) );
                btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                btn.setOnAction( (e)->{
                    try {
                        boolean b = new SystemReportController( ).deleteOrder( order.getOrderId( ) );
                        if (b){
                            new Alert( Alert.AlertType.CONFIRMATION,"Deleted !" ).show();
                            getAllOrders();
                        }
                    } catch ( SQLException throwables ) {
                        throwables.printStackTrace( );
                    } catch ( ClassNotFoundException notFoundException ) {
                        notFoundException.printStackTrace( );
                    }
                } );
            }
            tblOrder.setItems( orderTMS );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void txtOrderIdOnAction ( ActionEvent actionEvent ) {
        tblOrder.setItems( null );
        try {
            Order order = new SystemReportController( ).getOrder( txtOrderId.getText( ) );

            ObservableList< OrderTM > orderTMS = FXCollections.observableArrayList( );
            JFXButton btn = new JFXButton( "DELETE" );
            if (order!=null){
                orderTMS.add( new OrderTM(
                        order.getOrderId(),order.getDateTime(),
                        order.getTotal(),order.getCusId(),
                        order.getUserId(),btn
                ));
                btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                btn.setOnAction( (e)->{
                    try {
                        boolean b = new SystemReportController( ).deleteOrder( order.getOrderId( ) );
                        if (b){
                            new Alert( Alert.AlertType.CONFIRMATION,"Deleted !" ).show();
                            txtOrderId.setText( "" );
                            getAllOrders();
                        }
                    } catch ( SQLException throwables ) {
                        throwables.printStackTrace( );
                    } catch ( ClassNotFoundException notFoundException ) {
                        notFoundException.printStackTrace( );
                    }
                } );
                txtOrderId.setText( "" );
            }else {
                new Alert( Alert.AlertType.ERROR,"Empty Order !" ).show();
            }
            tblOrder.setItems( orderTMS );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    private void getAllOrderDetails(){
        try {
            List< OrderDetail > allOrderDetails = new SystemReportController( ).getAllOrderDetails( );
            ObservableList< OrderDetailTM > orderDetailTMS = FXCollections.observableArrayList( );
            for ( OrderDetail detail : allOrderDetails) {
                orderDetailTMS.add( new OrderDetailTM(
                        detail.getQty(),detail.getUnitPrice(),
                        detail.getOrderId(),detail.getPropertyId()
                ) );
            }
            tblOrderDetails.setItems( orderDetailTMS );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void txtOrderDetailsOnAction ( ActionEvent actionEvent ) {
        tblOrderDetails.setItems( null );
        try {
            List< OrderDetail > orderDetail = new SystemReportController( ).getOrderDetail( txtOrderDetails.getText( ) );
            ObservableList< OrderDetailTM > orderDetailTMS = FXCollections.observableArrayList( );
            if (orderDetail!=null){
                for ( OrderDetail detail : orderDetail) {
                    orderDetailTMS.add( new OrderDetailTM(
                            detail.getQty(),detail.getUnitPrice(),
                            detail.getOrderId(),detail.getPropertyId()
                    ) );
                }
                txtOrderDetails.setText( "" );
            }
            tblOrderDetails.setItems( orderDetailTMS );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }
}
