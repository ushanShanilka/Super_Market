package lk.ijse.supermarket.view.tm;


import com.jfoenix.controls.JFXButton;

public class TempOrderTM {
    private String propertyId;
    private String productName;
    private Double unitPrice;
    private int qty;
    private Double discount;
    private Double total;

    public TempOrderTM ( ) {
    }

    public TempOrderTM ( String propertyId , String productName , Double unitPrice , int qty , Double discount , Double total ) {
        this.propertyId = propertyId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
    }

    public String getPropertyId ( ) {
        return propertyId;
    }

    public void setPropertyId ( String propertyId ) {
        this.propertyId = propertyId;
    }

    public String getProductName ( ) {
        return productName;
    }

    public void setProductName ( String productName ) {
        this.productName = productName;
    }

    public Double getUnitPrice ( ) {
        return unitPrice;
    }

    public void setUnitPrice ( Double unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public int getQty ( ) {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public Double getDiscount ( ) {
        return discount;
    }

    public void setDiscount ( Double discount ) {
        this.discount = discount;
    }

    public Double getTotal ( ) {
        return total;
    }

    public void setTotal ( Double total ) {
        this.total = total;
    }

    @Override
    public String toString ( ) {
        return "TempOrderTM{" +
               "propertyId='" + propertyId + '\'' +
               ", productName='" + productName + '\'' +
               ", unitPrice=" + unitPrice +
               ", qty=" + qty +
               ", discount=" + discount +
               ", total=" + total +
               '}';
    }
}
