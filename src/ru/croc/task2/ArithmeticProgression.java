package ru.croc.task2;

public class ArithmeticProgression {
    public static void main(String[] args){

        int a = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);
        int S = 0;
        for (int i = 0; i<n; i++){
            S += a;
            a += d;
        }

        System.out.println(S);

    }

}
