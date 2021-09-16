package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.model.Customer;
import lk.ijse.supermarket.model.Order;
import lk.ijse.supermarket.model.OrderDetail;
import lk.ijse.supermarket.utils.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierController {
    CustomerController customerController = new CustomerController( );

    Connection connection = null;
    public boolean saveCustomer( Customer customer ) throws SQLException {

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit( false );
            PreparedStatement pst = connection.prepareStatement( "INSERT INTO Customer VALUES (?,?,?,?,?,?,?)" );
            Customer customer2 = customerController.getCustomer( customer.getCusId( ) );
            if (customer2!=null){
                boolean isUpdate = customerController.updateCustomer( customer );
                if (isUpdate){
                    boolean b = saveOrder( customer.getOrders( ));
                    if (b){
                        connection.commit();
                        return b;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }
            }else {
                pst.setObject( 1,customer.getCusId());
                pst.setObject( 2,customer.getCusType());
                pst.setObject( 3,customer.getCusName());
                pst.setObject( 4,customer.getCusAddress());
                pst.setObject( 5,customer.getCusCity());
                pst.setObject( 6,customer.getCusProvince());
                pst.setObject( 7,customer.getCusContact());
                boolean isCustomerSaved = pst.executeUpdate( )>0;
                if (isCustomerSaved){
                    boolean b = saveOrder( customer.getOrders( ));
                    if (b){
                        connection.commit();
                        return b;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }else {
                    connection.rollback();
                    return false;
                }
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }finally {
            connection.setAutoCommit( true );
        }
        return false;
    }

    public boolean saveOrder( ArrayList< Order > orders) throws SQLException, ClassNotFoundException {
        for ( Order order:orders) {
            PreparedStatement pstm = connection.prepareStatement( "INSERT INTO Orders VALUES (?,?,?,?,?)" );
            pstm.setObject( 1 , order.getOrderId() );
            pstm.setObject( 2 , order.getDateTime());
            pstm.setObject( 3 , order.getTotal());
            pstm.setObject( 4 , order.getCusId());
            pstm.setObject( 5 , order.getUserId());
            boolean isSaved = pstm.executeUpdate()>0;
            if (isSaved){
                    if (saveOrderDetail( order.getDetails( ) )){
                        connection.commit();
                    }else {
                        connection.rollback();
                        return false;
                    }
            }else {
                connection.rollback();
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetail( ArrayList< OrderDetail >  details ) throws SQLException, ClassNotFoundException {
        for ( OrderDetail detail : details) {
            PreparedStatement pst = connection.prepareStatement( "INSERT INTO Orderdetail VALUES (?,?,?,?)" );
            pst.setObject( 1,detail.getQty() );
            pst.setObject( 2,detail.getUnitPrice() );
            pst.setObject( 3,detail.getOrderId() );
            pst.setObject( 4,detail.getPropertyId() );
            boolean isSavedOrderDetail = pst.executeUpdate()>0;
            if (isSavedOrderDetail){
                if (updateQty( detail.getPropertyId(),detail.getQty() )){

                }else {
                    return false;
                }
            }else {
                return false;
            }

        }
        return true;
    }

    public boolean updateQty(String propertyId,int qty) throws SQLException {
        PreparedStatement pst = connection.prepareStatement( "UPDATE Batch SET quantity=(quantity-?) WHERE propertyId=?" );
        pst.setObject( 1,qty );
        pst.setObject( 2,propertyId );
        return pst.executeUpdate()>0;
    }

    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute( "SELECT id FROM Orders Order By id DESC LIMIT 1" );
        if (rst.next()){
            int tempId = Integer.parseInt( rst.getString( 1 ).split( "O" )[ 1 ] );
            tempId+=1;
            if (tempId < 10){
                return "O00"+tempId;
            }else if (tempId<100){
                return "O0"+tempId;
            }
        }
        return "O001";
    }


}
