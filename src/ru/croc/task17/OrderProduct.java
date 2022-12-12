package ru.croc.task17;

public class OrderProduct {


    private Product product;
    private Order order;

    public OrderProduct(Product product, Order order){
        this.product = product;
        this.order = order;
    }


    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }
}
