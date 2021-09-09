package lk.ijse.supermarket.model;

public class TempData {
    private String orderId;
    private String orderDate;
    private String cusId;
    private String cusType;
    private String cusName;
    private String cusAddress;
    private String cusCity;
    private String cusProvince;
    private int cusContact;
    private String cashierId;

    public TempData ( ) {
    }

    public TempData ( String orderId , String orderDate , String cusId , String cusType , String cusName , String cusAddress , String cusCity , String cusProvince , int cusContact , String cashierId ) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.cusType = cusType;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusCity = cusCity;
        this.cusProvince = cusProvince;
        this.cusContact = cusContact;
        this.cashierId = cashierId;
    }

    public String getOrderId ( ) {
        return orderId;
    }

    public void setOrderId ( String orderId ) {
        this.orderId = orderId;
    }

    public String getOrderDate ( ) {
        return orderDate;
    }

    public void setOrderDate ( String orderDate ) {
        this.orderDate = orderDate;
    }

    public String getCusId ( ) {
        return cusId;
    }

    public void setCusId ( String cusId ) {
        this.cusId = cusId;
    }

    public String getCusType ( ) {
        return cusType;
    }

    public void setCusType ( String cusType ) {
        this.cusType = cusType;
    }

    public String getCusName ( ) {
        return cusName;
    }

    public void setCusName ( String cusName ) {
        this.cusName = cusName;
    }

    public String getCusAddress ( ) {
        return cusAddress;
    }

    public void setCusAddress ( String cusAddress ) {
        this.cusAddress = cusAddress;
    }

    public String getCusCity ( ) {
        return cusCity;
    }

    public void setCusCity ( String cusCity ) {
        this.cusCity = cusCity;
    }

    public String getCusProvince ( ) {
        return cusProvince;
    }

    public void setCusProvince ( String cusProvince ) {
        this.cusProvince = cusProvince;
    }

    public int getCusContact ( ) {
        return cusContact;
    }

    public void setCusContact ( int cusContact ) {
        this.cusContact = cusContact;
    }

    public String getCashierId ( ) {
        return cashierId;
    }

    public void setCashierId ( String cashierId ) {
        this.cashierId = cashierId;
    }

    @Override
    public String toString ( ) {
        return "TempData{" +
               "orderId='" + orderId + '\'' +
               ", orderDate='" + orderDate + '\'' +
               ", cusId='" + cusId + '\'' +
               ", cusType='" + cusType + '\'' +
               ", cusName='" + cusName + '\'' +
               ", cusAddress='" + cusAddress + '\'' +
               ", cusCity='" + cusCity + '\'' +
               ", cusProvince='" + cusProvince + '\'' +
               ", cusContact=" + cusContact +
               ", cashierId='" + cashierId + '\'' +
               '}';
    }
}
