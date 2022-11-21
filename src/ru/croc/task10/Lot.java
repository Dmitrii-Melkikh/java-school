package ru.croc.task10;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Lot{
    private volatile int cost;
    private volatile String name;
    private Date endTime;
    private ReentrantLock lock = new ReentrantLock();

    public Lot(int cost, String name, Date endTime) {
        this.cost = cost;
        this.name = name;
        this.endTime = endTime;
        System.out.println(cost + name);
    }

    public void makeBid(int cost, String name) {
        lock.lock();
        if (cost > this.cost && new Date().before(this.endTime)) {
            this.cost = cost;
            this.name = name;
            }
        else if (new Date().before(this.endTime)){
            System.out.println("Your bid is too small");
        }
        else {
            System.out.println("Auction is end");
        }

        lock.unlock();
    }

    public String getWinner() {
        if (new Date().after(endTime))
            return this.name + " is winner";
        else
            return "The auction has not over yet";
    }

}
