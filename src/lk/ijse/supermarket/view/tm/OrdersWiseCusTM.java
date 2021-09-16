package lk.ijse.supermarket.view.tm;

public class OrdersWiseCusTM {
    private String cusId;
    private String orderId;
    private Double totalCost;

    public OrdersWiseCusTM() {
    }

    public OrdersWiseCusTM(String cusId, String orderId, Double totalCost) {
        this.cusId = cusId;
        this.orderId = orderId;
        this.totalCost = totalCost;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "OrdersWiseCusTM{" +
                "cusId='" + cusId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
