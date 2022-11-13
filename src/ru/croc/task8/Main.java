package ru.croc.task8;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Locale russia = new Locale("ru", "RU");
        NumberFormat rubles = NumberFormat.getCurrencyInstance(russia);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a double: ");
        if (sc.hasNextDouble()){
            double value = sc.nextDouble();
            System.out.println("Result: " + rubles.format(value));
        }
        else{
            System.out.println("It's not a double...");
        }




    }
}
