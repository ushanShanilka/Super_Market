package lk.ijse.supermarket.model;


public class TempTable {
    private String orderId;
    private String propertyId;
    private String productName;
    private Double unitPrice;
    private int qty;
    private Double discount;
    private Double subTotal;

    public TempTable ( ) {
    }

    public TempTable ( String orderId , String propertyId , String productName , Double unitPrice , int qty , Double discount , Double subTotal ) {
        this.orderId = orderId;
        this.propertyId = propertyId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.subTotal = subTotal;
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

    public Double getSubTotal ( ) {
        return subTotal;
    }

    public void setSubTotal ( Double subTotal ) {
        this.subTotal = subTotal;
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
               ", subTotal=" + subTotal +
               '}';
    }
}
