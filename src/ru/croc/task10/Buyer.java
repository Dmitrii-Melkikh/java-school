package ru.croc.task10;

public class Buyer implements Runnable {

    private String name;
    private int cost;
    private Lot lot;

    public Buyer(String name, int cost, Lot lot){
        this.name = name;
        this.cost = cost;
        this.lot = lot;
    }

    @Override
    public void run(){
        while(true){
            lot.makeBid(cost, name);}
    }
}
