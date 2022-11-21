package ru.croc.task9;

public class Main {
    public static volatile String password;
    public static volatile String hashPassword;

    public static void main(String[] args) {
        int countThreads = Integer.parseInt(args[0]); //Количество потоков
        hashPassword = args[1];
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(new CalculatePassw(countThreads, i + 1, 7));
            threads[i].start();
        }

        for (int i = 0; i < countThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Password is found: " + password);

    }
}