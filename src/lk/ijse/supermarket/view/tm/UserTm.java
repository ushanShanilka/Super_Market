package lk.ijse.supermarket.view.tm;

import com.jfoenix.controls.JFXButton;

public class UserTm {
    private int userId;
    private String userName;
    private String password;
    private Boolean activeState;
    private String userType;
    private JFXButton btn;

    public UserTm ( ) {
    }

    public UserTm ( int userId , String userName , String password , Boolean activeState , String userType , JFXButton btn ) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.activeState = activeState;
        this.userType = userType;
        this.btn = btn;
    }

    public int getUserId ( ) {
        return userId;
    }

    public void setUserId ( int userId ) {
        this.userId = userId;
    }

    public String getUserName ( ) {
        return userName;
    }

    public void setUserName ( String userName ) {
        this.userName = userName;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public Boolean getActiveState ( ) {
        return activeState;
    }

    public void setActiveState ( Boolean activeState ) {
        this.activeState = activeState;
    }

    public String getUserType ( ) {
        return userType;
    }

    public void setUserType ( String userType ) {
        this.userType = userType;
    }

    public JFXButton getBtn ( ) {
        return btn;
    }

    public void setBtn ( JFXButton btn ) {
        this.btn = btn;
    }

    @Override
    public String toString ( ) {
        return "UserTm{" +
               "userId=" + userId +
               ", userName='" + userName + '\'' +
               ", password='" + password + '\'' +
               ", activeState=" + activeState +
               ", userType='" + userType + '\'' +
               ", btn=" + btn +
               '}';
    }
}
