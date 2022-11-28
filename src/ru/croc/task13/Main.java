package ru.croc.task13;


import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IDs of the movies you have watched: ");
        String inputIds = sc.next();
        System.out.println(Recommendation.findRecommendation(inputIds));

    }
}
