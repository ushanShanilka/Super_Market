package lk.ijse.supermarket.view.tm;

public class CustomerTM {
    private String cusId;
    private String cusType;
    private String cusName;
    private String cusAddress;
    private String cusCity;
    private String cusProvince;
    private int cusContact;

    public CustomerTM ( ) {
    }

    public CustomerTM ( String cusId , String cusType , String cusName , String cusAddress , String cusCity , String cusProvince , int cusContact ) {
        this.cusId = cusId;
        this.cusType = cusType;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusCity = cusCity;
        this.cusProvince = cusProvince;
        this.cusContact = cusContact;
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

    @Override
    public String toString ( ) {
        return "CustomerTM{" +
               "cusId='" + cusId + '\'' +
               ", cusType='" + cusType + '\'' +
               ", cusName='" + cusName + '\'' +
               ", cusAddress='" + cusAddress + '\'' +
               ", cusCity='" + cusCity + '\'' +
               ", cusProvince='" + cusProvince + '\'' +
               ", cusContact=" + cusContact +
               '}';
    }
}
