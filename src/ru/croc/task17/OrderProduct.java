package ru.croc.task17;

public class OrderProduct {

    private String articleId;
    private int orderId;

    public OrderProduct(String articleId, int orderId){
        this.articleId = articleId;
        this.orderId = orderId;
    }

    public String getArticleId() {
        return articleId;
    }

    public int getOrderId() {
        return orderId;
    }
    @Override
    public String toString(){
        return articleId + " " + orderId;
    }
}
