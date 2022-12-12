package ru.croc.task17;

public class Order {
    private int orderId;
    private String userName;


    public Order(int orderId, String userName) {
        this.orderId = orderId;
        this.userName = userName;

    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }
    @Override
    public String toString(){
        return orderId + " " + userName;
    }

}
