package ru.croc.task10;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static void main(String[] args) {
        Random r = new Random();
        String name;
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime date  = today.plusSeconds(30);
        Thread[] threads = new Thread[4];
        Lot lot = new Lot(1, "I", date);
        for (int i = 0; i < 4; i++){
            name = generateString(r, "abcdefghklo", 5);
            threads[i] = new Thread(new Buyer(name, lot));
            threads[i].start();
        }
        try {
            for (int i = 0; i < 4; i++) {
                threads[i].join();
            }
        }
        catch (InterruptedException e){
            System.out.println("Error");
        }
        System.out.println(lot.getWinner());
    }
}
