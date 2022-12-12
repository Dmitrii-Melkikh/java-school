package ru.croc.task17;


import java.math.BigDecimal;

public class Product {
    private String articleId;
    private String name;
    private BigDecimal cost;

    public Product(String articleId, String name, BigDecimal cost) {
        this.articleId = articleId;
        this.name = name;
        this.cost = cost;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
    @Override
    public String toString(){
        return articleId + " " + name + " " + cost;
    }
}
