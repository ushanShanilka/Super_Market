package lk.ijse.supermarket.model;

import java.math.BigDecimal;


public class Item {
    private String propertyId;
    private String batch;
    private BigDecimal price;
    private boolean discountState;
    private BigDecimal discount;
    private boolean activeState ;
    private int qty;
    private String dateTime;
    private Object productId;

    public Item ( ) {
    }

    public Item ( String propertyId , String batch , BigDecimal price , boolean discountState , BigDecimal discount , boolean activeState , int qty , String dateTime , Object productId ) {
        this.propertyId = propertyId;
        this.batch = batch;
        this.price = price;
        this.discountState = discountState;
        this.discount = discount;
        this.activeState = activeState;
        this.qty = qty;
        this.dateTime = dateTime;
        this.productId = productId;
    }

    public String getPropertyId ( ) {
        return propertyId;
    }

    public void setPropertyId ( String propertyId ) {
        this.propertyId = propertyId;
    }

    public String getBatch ( ) {
        return batch;
    }

    public void setBatch ( String batch ) {
        this.batch = batch;
    }

    public BigDecimal getPrice ( ) {
        return price;
    }

    public void setPrice ( BigDecimal price ) {
        this.price = price;
    }

    public boolean isDiscountState ( ) {
        return discountState;
    }

    public void setDiscountState ( boolean discountState ) {
        this.discountState = discountState;
    }

    public BigDecimal getDiscount ( ) {
        return discount;
    }

    public void setDiscount ( BigDecimal discount ) {
        this.discount = discount;
    }

    public boolean isActiveState ( ) {
        return activeState;
    }

    public void setActiveState ( boolean activeState ) {
        this.activeState = activeState;
    }

    public int getQty ( ) {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public String getDateTime ( ) {
        return dateTime;
    }

    public void setDateTime ( String dateTime ) {
        this.dateTime = dateTime;
    }

    public Object getProductId ( ) {
        return productId;
    }

    public void setProductId ( Object productId ) {
        this.productId = productId;
    }

    @Override
    public String toString ( ) {
        return "Item{" +
               "propertyId='" + propertyId + '\'' +
               ", batch='" + batch + '\'' +
               ", price=" + price +
               ", discountState=" + discountState +
               ", discount=" + discount +
               ", activeState=" + activeState +
               ", qty=" + qty +
               ", dateTime='" + dateTime + '\'' +
               ", productId=" + productId +
               '}';
    }
}
