package ru.croc.task9;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.ReentrantLock;


public class CalculatePassw implements Runnable{

    private final int countThreads; //Количество потоков
    private final int threadNumber;    //Номер потока
    private final int passwLength;      //Длина пароля
    private long begin;                //Начало и конец конкретного потока
    private long end;
    private final long countOptions;//Количество возможных вариантов паролей
    private final int sizeOfAlphabet = 26; //размер алфавита
    private static volatile boolean passFound = false; // Флаг нахождения нужного пароля
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();



    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    public CalculatePassw(int countThreads, int threadNumber, int passwLength){
        this.countThreads = countThreads;
        this.threadNumber = threadNumber;
        this.passwLength = passwLength;
        this.countOptions = (long)Math.pow(sizeOfAlphabet, passwLength);
        this.begin = ((long) countOptions * (threadNumber - 1)) / countThreads;
        this.end = (((long) countOptions * threadNumber) / countThreads) - 1;
    }

    private String generatePassw(long n){

        char[] password = new char[passwLength];
        for(int i = 0; i < passwLength; i++) {
            password[i] = (char) ('a' + (n % sizeOfAlphabet) );
            n /= sizeOfAlphabet;
        }
        String pass = new String(password);
        return pass;
    }

    @Override
    public void run(){
        if (!passFound) {
            for (long i = begin; i < end; i++) {
                String password = generatePassw(i);
                if (hashPassword(password).equals(Main.hashPassword)) {
                    Main.password = password;
                    passFound = true;
                    break;
                }
            }
        }
    }



}
