package ru.croc.task10;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
        int x;
        String name;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,17);
        cal.set(Calendar.MINUTE,30);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date date = cal.getTime();
        Thread[] threads = new Thread[4];
        Lot lot = new Lot(1, "I", date);
        for (int i = 0; i < 4; i++){
            x = r.nextInt(100);
            name = generateString(r, "abcdefghklo", 5);
            threads[i] = new Thread(new Buyer(name, x, lot));
            threads[i].start();
        }

    }
}
