package lk.ijse.supermarket.model;

public class OrderDetail {
    private int qty;
    private Double unitPrice;
    private String orderId;
    private String propertyId;

    public OrderDetail ( ) {
    }

    public OrderDetail ( int qty , Double unitPrice , String orderId , String propertyId ) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.orderId = orderId;
        this.propertyId = propertyId;
    }

    public int getQty ( ) {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public Double getUnitPrice ( ) {
        return unitPrice;
    }

    public void setUnitPrice ( Double unitPrice ) {
        this.unitPrice = unitPrice;
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

    @Override
    public String toString ( ) {
        return "OrderDetail{" +
               "qty=" + qty +
               ", unitPrice=" + unitPrice +
               ", orderId='" + orderId + '\'' +
               ", propertyId='" + propertyId + '\'' +
               '}';
    }
}
