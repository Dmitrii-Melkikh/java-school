package ru.croc.task10;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Lot{
    private volatile int cost;
    private volatile String name;
    public LocalDateTime endTime;
    private ReentrantLock lock = new ReentrantLock();

    public Lot(int cost, String name, LocalDateTime endTime) {
        this.cost = cost;
        this.name = name;
        this.endTime = endTime;
    }

    public void makeBid(int cost, String name) {
        lock.lock();
        if (cost > this.cost && LocalDateTime.now().isBefore(this.endTime)) {
            this.cost = cost;
            this.name = name;
            }
        else if (LocalDateTime.now().isBefore(this.endTime)){
            System.out.println("Your bid is too small");
        }
        else {
            System.out.println("Auction is end");
        }
        lock.unlock();
    }

    public String getWinner() {
        if (endTime.isBefore(LocalDateTime.now()))
            return this.name + " is winner" ;
        else
            return new Date() + "The auction has not over yet";
    }

}
