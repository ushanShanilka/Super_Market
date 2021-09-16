package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.supermarket.model.Customer;
import lk.ijse.supermarket.model.Order;
import lk.ijse.supermarket.model.OrderDetail;
import lk.ijse.supermarket.view.tm.OrderAnnualTM;
import lk.ijse.supermarket.view.tm.OrderDetailTM;
import lk.ijse.supermarket.view.tm.OrderTM;
import lk.ijse.supermarket.view.tm.OrdersWiseCusTM;

import java.sql.SQLException;
import java.time.LocalDate;
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
    public JFXTextField txtCusId;
    public JFXComboBox cmbSelect;
    public TableView tblCusWiseIncome;
    public JFXButton btnCusWiseIncome;
    public JFXComboBox cmbCusId;
    public TableColumn colCusId;
    public TableColumn colOrderId;
    public TableColumn colTotalCost;
    public Label lblTotal;
    public TableView tblAnnualIncome;
    public TableColumn colIncomeOrderId;
    public TableColumn colIncomeDate;
    public TableColumn colIncomeTot;
    public TableColumn colIncomeCusId;
    public TableColumn colIncomeUserId;
    public JFXComboBox cmbAnnual;
    public JFXButton btnAnnualIncome;
    public Label lblAnnualTot;
    public JFXDatePicker datePicker;
    public JFXDatePicker pickerToDate;

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

        ObservableList<Object> observableArrayList = FXCollections.observableArrayList();
        observableArrayList.add("By OrderId");
        observableArrayList.add("By Customer");
        cmbSelect.setItems(observableArrayList);


        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        getAllOrderWiseCusId();

        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add("Daily");
        list.add("Monthly");
        cmbAnnual.setItems(list);

        colIncomeOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colIncomeDate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colIncomeTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        colIncomeCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colIncomeUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    public void btnOrderOnAction ( ActionEvent actionEvent ) {
        tblOrder.setVisible( true );
        tblOrderDetails.setVisible( false );
        tblCusWiseIncome.setVisible(false);
        txtOrderId.setVisible( true );
        txtOrderDetails.setVisible( false );
        cmbSelect.setVisible(true);
        txtCusId.setVisible(true);
        cmbCusId.setVisible(false);
        lblTotal.setVisible(false);
        cmbAnnual.setVisible(false);
        tblAnnualIncome.setVisible(false);
        lblAnnualTot.setVisible(false);
        datePicker.setVisible(false);
        pickerToDate.setVisible(false);
        getAllOrders();
    }

    public void btnOrderDetailsOnAction ( ActionEvent actionEvent ) {
        tblOrder.setVisible( false );
        tblOrderDetails.setVisible( true );
        tblCusWiseIncome.setVisible(false);
        txtOrderId.setVisible( false );
        txtOrderDetails.setVisible( true );
        cmbSelect.setVisible(false);
        txtCusId.setVisible(false);
        cmbCusId.setVisible(false);
        lblTotal.setVisible(false);
        cmbAnnual.setVisible(false);
        tblAnnualIncome.setVisible(false);
        lblAnnualTot.setVisible(false);
        datePicker.setVisible(false);
        pickerToDate.setVisible(false);
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

    public void txtCusIdOnAction(ActionEvent actionEvent) {
        try {
            List<Order> allOrdersByCusId = new SystemReportController().getAllOrdersByCusId(txtCusId.getText());

            ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();

            for (Order order :allOrdersByCusId ){
                JFXButton btn = new JFXButton( "DELETE" );
                orderTMS.add(new OrderTM(
                        order.getOrderId(),
                        order.getDateTime(),
                        order.getTotal(),
                        order.getCusId(),
                        order.getUserId(),
                        btn
                ));
                btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                btn.setOnAction( (e)->{
                    try {
                        boolean b = new SystemReportController( ).deleteOrder( order.getOrderId( ) );
                        if (b){
                            new Alert( Alert.AlertType.CONFIRMATION,"Deleted !" ).show();
                            txtCusId.setText( "" );
                            getAllOrders();
                        }
                    } catch ( SQLException throwables ) {
                        throwables.printStackTrace( );
                    } catch ( ClassNotFoundException notFoundException ) {
                        notFoundException.printStackTrace( );
                    }
                } );
            }
            tblOrder.setItems(orderTMS);
            txtCusId.setText("");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbSelectOnAction(ActionEvent actionEvent) {
        Object value = cmbSelect.getValue();
        if (value=="By OrderId"){
            txtCusId.setVisible(false);
            txtOrderId.setVisible(true);
        }else if (value=="By Customer"){
            txtCusId.setVisible(true);
            txtOrderId.setVisible(false);
        }
    }

    public void btnCusWiseIncomeOnAction(ActionEvent actionEvent) {
        tblOrder.setVisible( false );
        tblOrderDetails.setVisible( false );
        tblCusWiseIncome.setVisible(true);
        txtOrderId.setVisible( false );
        txtOrderDetails.setVisible( false );
        cmbSelect.setVisible(false);
        txtCusId.setVisible(false);
        cmbCusId.setVisible(true);
        lblTotal.setVisible(true);
        cmbAnnual.setVisible(false);
        tblAnnualIncome.setVisible(false);
        lblAnnualTot.setVisible(false);
        datePicker.setVisible(false);
        pickerToDate.setVisible(false);
    }

    private void getAllOrderWiseCusId(){
        try {
            List<Customer> allCustomers = new CustomerController().getAllCustomers();
            ObservableList<String > cusId = FXCollections.observableArrayList();

            for (Customer customer:allCustomers ) {
                cusId.add(customer.getCusId());
            }
            cmbCusId.setItems(cusId);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void selectCusIdOnAction(ActionEvent actionEvent) {
        try {
            List<Order> ordersByCusId = new SystemReportController().getAllOrdersByCusId(String.valueOf(cmbCusId.getValue()));
            ObservableList<OrdersWiseCusTM> wiseCusTMS = FXCollections.observableArrayList();

            double totalCost = 0.00;
            for (Order order :ordersByCusId) {
                wiseCusTMS.add(new OrdersWiseCusTM(
                        order.getCusId(),
                        order.getOrderId(),
                        order.getTotal()
                ));
                totalCost+=order.getTotal();
            }

            lblTotal.setText("Total : "+totalCost+"LKR");
            tblCusWiseIncome.setItems(wiseCusTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbAnnualOnAction(ActionEvent actionEvent) {
        pickerToDate.setVisible(false);

        try {
            double total = 00.00;
        Object value = cmbAnnual.getValue();
        if (value =="Daily"){
            datePicker.setVisible(true);
            datePicker.setValue(LocalDate.now());
            List<Order> allOrderByDaily = new SystemReportController().getAllOrderByDaily(String.valueOf(LocalDate.now()));
            ObservableList<OrderAnnualTM> orderAnnualTMS = FXCollections.observableArrayList();

            for (Order order :allOrderByDaily) {
                orderAnnualTMS.add(new OrderAnnualTM(
                        order.getOrderId(),
                        order.getDateTime(),
                        order.getTotal(),
                        order.getCusId(),
                        order.getUserId()
                ));
                total+=order.getTotal();
            }
            lblAnnualTot.setText("Total : "+total+"LKR");
            tblAnnualIncome.setItems(orderAnnualTMS);
        }else if (value == "Monthly"){
            datePicker.setVisible(true);
            pickerToDate.setVisible(true);
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAnnualIncomeOnAction(ActionEvent actionEvent) {
        tblOrder.setVisible( false );
        tblOrderDetails.setVisible( false );
        tblCusWiseIncome.setVisible(false);
        txtOrderId.setVisible( false );
        txtOrderDetails.setVisible( false );
        cmbSelect.setVisible(false);
        txtCusId.setVisible(false);
        cmbCusId.setVisible(false);
        lblTotal.setVisible(false);
        cmbAnnual.setVisible(true);
        tblAnnualIncome.setVisible(true);
        lblAnnualTot.setVisible(true);
    }

    public void getMonthlyOrdersOnAction(ActionEvent actionEvent) {
        double total = 00.00;
        try {

            LocalDate value = datePicker.getValue();
            LocalDate value1 = pickerToDate.getValue();

            List<Order> allOrderByDaily = new SystemReportController().getAllOrderByMonthly(String.valueOf(value),String.valueOf(value1));

        ObservableList<OrderAnnualTM> orderAnnualTMS = FXCollections.observableArrayList();

        for (Order order :allOrderByDaily) {
            orderAnnualTMS.add(new OrderAnnualTM(
                    order.getOrderId(),
                    order.getDateTime(),
                    order.getTotal(),
                    order.getCusId(),
                    order.getUserId()
            ));
            total+=order.getTotal();
        }
        lblAnnualTot.setText("Total : "+total+"LKR");
        tblAnnualIncome.setItems(orderAnnualTMS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
