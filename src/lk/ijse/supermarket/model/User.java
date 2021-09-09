package lk.ijse.supermarket.model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private Boolean activeState;
    private String userType;

    public User ( ) {
    }

    public User ( int userId , String userName , String password , Boolean activeState , String userType ) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.activeState = activeState;
        this.userType = userType;
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

    @Override
    public String toString ( ) {
        return "User{" +
               "userId=" + userId +
               ", userName='" + userName + '\'' +
               ", password='" + password + '\'' +
               ", activeState=" + activeState +
               ", userType='" + userType + '\'' +
               '}';
    }
}
