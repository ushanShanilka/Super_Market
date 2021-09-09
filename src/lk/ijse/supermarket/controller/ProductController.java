package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.model.Product;
import lk.ijse.supermarket.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public boolean saveProduct( Product product ) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "INSERT INTO Product VALUES (?,?,?,?,?,?,?,?)",product.getProductId (),product.getName (),
                                  product.getDescription (),product.getSpec (),
                                  product.getDisplayName (),product.isAvailability (),
                                  product.isActiveState (),product.getBrands ());
    }

    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "DELETE FROM Product WHERE id=?",productId );
    }

    public List<Product> getAllActiveState() throws SQLException, ClassNotFoundException {
        ArrayList< Product > productArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product WHERE ActiveState=1" );

        while (rst.next ()) {
            productArrayList.add (
            new Product (
                    rst.getString ( 1 ) , rst.getString ( 2 ) ,
                    rst.getString ( 3 ) , rst.getString ( 4 ) ,
                    rst.getString ( 5 ) , rst.getBoolean ( 6 ) ,
                    rst.getBoolean ( 7 ) , rst.getString ( 8 )
            )
            );
        }
        return productArrayList;
    }

    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList< Product > productArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product" );

        while (rst.next ()) {
            productArrayList.add (
                    new Product (
                            rst.getString ( 1 ) , rst.getString ( 2 ) ,
                            rst.getString ( 3 ) , rst.getString ( 4 ) ,
                            rst.getString ( 5 ) , rst.getBoolean ( 6 ) ,
                            rst.getBoolean ( 7 ) , rst.getString ( 8 )
                    )
            );
        }
        return productArrayList;
    }

    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "UPDATE Product SET name=?, description=?, specification=?, displayName=?, available=?, activeState=?, availableBrands=? WHERE id=?",product.getName (),product.getDescription (),product.getSpec (),product.getDisplayName (),product.isAvailability (),product.isActiveState (),product.getBrands (),product.getProductId () );
    }
    
    public Product searchProduct(String productId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product WHERE id=?" , productId );
        if (rst.next ()){
            return new Product (
                    rst.getString ( 1 ),
                    rst.getString (  2),
                    rst.getString (  3),
                    rst.getString (  4),
                    rst.getString (  5),
                    rst.getBoolean ( 6 ),
                    rst.getBoolean ( 7 ),
                    rst.getString (  8)
            );
        }
        return null;
    }

}
