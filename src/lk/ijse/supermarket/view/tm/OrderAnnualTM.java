package lk.ijse.supermarket.view.tm;

public class OrderAnnualTM {
    private String orderId;
    private String dateTime;
    private Double total;
    private String cusId;
    private int userId;

    public OrderAnnualTM() {
    }

    public OrderAnnualTM(String orderId, String dateTime, Double total, String cusId, int userId) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.total = total;
        this.cusId = cusId;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderAnnualTM{" +
                "orderId='" + orderId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", total=" + total +
                ", cusId='" + cusId + '\'' +
                ", userId=" + userId +
                '}';
    }
}
