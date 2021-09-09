package lk.ijse.supermarket.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.supermarket.model.User;
import lk.ijse.supermarket.utils.CrudUtil;
import lk.ijse.supermarket.view.tm.UserTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    public boolean saveUser( User user ) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "INSERT INTO User VALUES (?,?,?,?,?)",user.getUserId (),user.getUserName (),user.getPassword (),user.getActiveState (),user.getUserType () );
    }

    public User searchUser(String userId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE id=?" , userId );
        if (rst.next ()){
            return new User (
                    rst.getInt ( 1 ),
                    rst.getString ( 2 ),
                    rst.getString ( 3 ),
                    rst.getBoolean ( 4 ),
                    rst.getString ( 5 )
            );
        }
        return null;
    }

    public boolean deleteUser ( int userId ) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "DELETE FROM User WHERE id=?",userId );
    }

    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute ( "UPDATE User SET name=?, password=?, active_state=?,userType=? WHERE id=?" , user.getUserName ( ) , user.getPassword ( ) , user.getActiveState ( ) , user.getUserType ( ) , user.getUserId ( ) );
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User" );
        ArrayList< User >  users = new ArrayList<> ( );
        while (rst.next ()){
            users.add (
                    new User (
                            rst.getInt ( 1 ),rst.getString ( 2 ),
                            rst.getString ( 3 ),rst.getBoolean ( 4 ),
                            rst.getString ( 5 )
                    )
            );
        }
        return users;
    }

    public User getActiveUsers(String name,String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE id=? AND  password=? AND active_state=1",name,password );
        if (rst.next ()){
            return new User (
                    rst.getInt ( 1 ),rst.getString ( 2 ),
                    rst.getString ( 3 ),rst.getBoolean ( 4 ),
                    rst.getString ( 5 )
            );
        }
        return null;
    }

    public List<User> getAllActiveUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE active_state=1" );
        ArrayList< User > activeUsersList = new ArrayList<> ( );

        while (rst.next ()){
            activeUsersList.add (
                    new User (
                            rst.getInt ( 1 ),rst.getString ( 2 ),
                            rst.getString ( 3 ),rst.getBoolean ( 4 ),
                            rst.getString ( 5 )
                    )
            );
        }
        return activeUsersList;
    }
}
