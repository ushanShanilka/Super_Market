package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.model.Item;
import lk.ijse.supermarket.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public boolean saveBatch( Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "INSERT INTO Batch VALUES (?,?,?,?,?,?,?,?,?)",item.getPropertyId (),
                                  item.getBatch (),item.getPrice (),
                                  item.isDiscountState (),item.getDiscount (),
                                  item.isActiveState (),item.getQty (),
                                  item.getDateTime (),item.getProductId ()
        );
    }

    public List<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList< Item > itemArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Batch" );

        while (rst.next ()){
            itemArrayList.add (
                    new Item (
                            rst.getString ( 1 ),rst.getString(2),
                            rst.getBigDecimal ( 3 ),rst.getBoolean ( 4 ),
                            rst.getBigDecimal ( 5 ),rst.getBoolean ( 6 ),
                            rst.getInt ( 7 ),rst.getString ( 8 ),
                            rst.getString ( 9 )
                    )
            );
        }
        return itemArrayList;
    }

    public List<Item> getAllActiveStateItems() throws SQLException, ClassNotFoundException {
        ArrayList< Item > itemArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Batch WHERE activeState=1" );

        while (rst.next ()){
            itemArrayList.add (
                    new Item (
                            rst.getString ( 1 ),rst.getString(2),
                            rst.getBigDecimal ( 3 ),rst.getBoolean ( 4 ),
                            rst.getBigDecimal ( 5 ),rst.getBoolean ( 6 ),
                            rst.getInt ( 7 ),rst.getString ( 8 ),
                            rst.getString ( 9 )
                    )
            );
        }
        return itemArrayList;
    }

    public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "UPDATE Batch SET batch=?,price=?,discountState=?,discount=?,activeState=?,quantity=?,systemDate=?,productId=? WHERE propertyId=?",item.getBatch (),item.getPrice (),item.isDiscountState (),item.getDiscount (),item.isActiveState (),item.getQty (),item.getDateTime (),item.getBatch (),item.getPropertyId () );
    }

    public Item searchItem(String propertyId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Batch WHERE propertyId=?" , propertyId );

        while (rst.next ()){
            return new Item (
                    rst.getString ( 1 ),
                    rst.getString ( 2 ),
                    rst.getBigDecimal ( 3 ),
                    rst.getBoolean ( 4 ),
                    rst.getBigDecimal ( 5 ),
                    rst.getBoolean ( 6 ),
                    rst.getInt ( 7 ),
                    rst.getString ( 8 ),
                    rst.getString ( 9 )
            );
        }
        return null;
    }

    public boolean deleteItem(String propertyId) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute ( "DELETE FROM Batch WHERE propertyId=?",propertyId );
    }

    public ResultSet autoGenerateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT propertyId FROM Batch ORDER BY propertyId DESC LIMIT 1" );
        return rst;
    }
}
