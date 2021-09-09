package lk.ijse.supermarket.model;

import java.math.BigDecimal;

public class TempTable {
    private String orderId;
    private String propertyId;
    private String productName;
    private BigDecimal unitPrice;
    private int qty;
    private BigDecimal discount;
    private BigDecimal total;

    public TempTable ( ) {
    }

    public TempTable ( String orderId , String propertyId , String productName , BigDecimal unitPrice , int qty , BigDecimal discount , BigDecimal total ) {
        this.orderId = orderId;
        this.propertyId = propertyId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
    }

    public String getOrderId ( ) {
        return orderId;
    }

    public void setOrderId ( String orderId ) {
        this.orderId = orderId;
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

    public BigDecimal getUnitPrice ( ) {
        return unitPrice;
    }

    public void setUnitPrice ( BigDecimal unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public int getQty ( ) {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public BigDecimal getDiscount ( ) {
        return discount;
    }

    public void setDiscount ( BigDecimal discount ) {
        this.discount = discount;
    }

    public BigDecimal getTotal ( ) {
        return total;
    }

    public void setTotal ( BigDecimal total ) {
        this.total = total;
    }

    @Override
    public String toString ( ) {
        return "TempTable{" +
               "orderId='" + orderId + '\'' +
               ", propertyId='" + propertyId + '\'' +
               ", productName='" + productName + '\'' +
               ", unitPrice=" + unitPrice +
               ", qty=" + qty +
               ", discount=" + discount +
               ", total=" + total +
               '}';
    }
}
