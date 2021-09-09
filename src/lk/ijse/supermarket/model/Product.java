package lk.ijse.supermarket.model;

public class Product {
    private String productId;
    private String name;
    private String description;
    private String spec;
    private String displayName;
    private boolean availability;
    private boolean activeState;
    private String brands;

    public Product ( ) {
    }

    public Product ( String productId , String name , String description , String spec , String displayName , boolean availability , boolean activeState , String brands ) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.displayName = displayName;
        this.availability = availability;
        this.activeState = activeState;
        this.brands = brands;
    }

    public String getProductId ( ) {
        return productId;
    }

    public void setProductId ( String productId ) {
        this.productId = productId;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getDescription ( ) {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public String getSpec ( ) {
        return spec;
    }

    public void setSpec ( String spec ) {
        this.spec = spec;
    }

    public String getDisplayName ( ) {
        return displayName;
    }

    public void setDisplayName ( String displayName ) {
        this.displayName = displayName;
    }

    public boolean isAvailability ( ) {
        return availability;
    }

    public void setAvailability ( boolean availability ) {
        this.availability = availability;
    }

    public boolean isActiveState ( ) {
        return activeState;
    }

    public void setActiveState ( boolean activeState ) {
        this.activeState = activeState;
    }

    public String getBrands ( ) {
        return brands;
    }

    public void setBrands ( String brands ) {
        this.brands = brands;
    }

    @Override
    public String toString ( ) {
        return "Product{" +
               "productId='" + productId + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", spec='" + spec + '\'' +
               ", displayName='" + displayName + '\'' +
               ", availability=" + availability +
               ", activeState=" + activeState +
               ", brands='" + brands + '\'' +
               '}';
    }
}
