package ru.croc.task10;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class Buyer implements Runnable {

    private String name;

    private Lot lot;

    public Buyer(String name, Lot lot){
        this.name = name;
        this.lot = lot;
    }

    @Override
    public void run(){
        Random r = new Random();
        while(true){
            int cost = r.nextInt(100000);
            LocalDateTime current = LocalDateTime.now();
            if (current.isBefore(lot.endTime)){
                lot.makeBid(cost, name);
            }
            else{
                break;
            }

        }
    }
}
