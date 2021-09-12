package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.model.Order;
import lk.ijse.supermarket.model.OrderDetail;
import lk.ijse.supermarket.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemReportController {

    public List< Order >  getAllOrders() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM Orders" );
        ArrayList< Order > list = new ArrayList<>( );
        while (rst.next()){
            list.add(
                    new Order(
                            rst.getString( 1 ),
                            rst.getString( 2 ),
                            rst.getDouble( 3 ),
                            rst.getString( 4 ),
                            rst.getInt( 5 )
                    )
            );
        }
        return list;
    }

    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "DELETE FROM Orders WHERE id=? ",id );
    }

    public Order getOrder(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM Orders WHERE id=?" , id );
        while (rst.next()){
            return new Order(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getDouble( 3 ),
                    rst.getString( 4 ),
                    rst.getInt( 5 )
            );
        }
        return null;
    }

    public List< OrderDetail > getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM OrderDetail" );
        ArrayList< OrderDetail > list = new ArrayList<>( );

        while (rst.next()){
            list.add( new OrderDetail(
                    rst.getInt( 1 ),
                    rst.getDouble( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 )
            ) );
        }
        return list;
    }

    public List<OrderDetail> getOrderDetail(String id ) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM OrderDetail WHERE orderId=?" , id );
        ArrayList< OrderDetail > list = new ArrayList<>( );
        while (rst.next()){
            list.add( new OrderDetail(
                    rst.getInt( 1 ),
                    rst.getDouble( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 )
            ) );
        }
        return list;
    }
}
