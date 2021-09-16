package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.model.Customer;
import lk.ijse.supermarket.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    public Customer getCustomer( String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM Customer WHERE id=?" , id );
        while (rst.next()){
            return new Customer(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 ),
                    rst.getString( 5 ),
                    rst.getString( 6 ),
                    rst.getInt(7 )
            );
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "UPDATE Customer SET name=?, customerType=?, address=?, city=?, province=?, contact=? WHERE id=?" ,
                                 customer.getCusName( ) , customer.getCusType( ) ,
                                 customer.getCusAddress( ) , customer.getCusCity( ) ,
                                 customer.getCusProvince( ) , customer.getCusContact( ),
                                 customer.getCusId());
    }

    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM Customer" );
        ArrayList< Customer > list = new ArrayList<>( );

        while (rst.next()){
            list.add( new Customer(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 ),
                    rst.getString( 5 ),
                    rst.getString( 6 ),
                    rst.getInt( 7 )
            ) );
        }
        return list;
    }

    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute( "SELECT id FROM Customer Order By id DESC LIMIT 1" );
        if (rst.next()){
            int tempId = Integer.parseInt( rst.getString( 1 ).split( "C" )[ 1 ] );
            tempId+=1;
            if (tempId < 99){
                return "C00"+tempId;
            }else {
                return "O0"+tempId;
            }
        }
        return "C001";
    }
}
