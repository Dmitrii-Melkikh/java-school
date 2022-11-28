package ru.croc.task13;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GetInfo {

    private static final String films = "src/ru/croc/task13/films.txt";
    private static final String historyOfViews = "src/ru/croc/task13/views.txt";


    public static HashMap<Integer,String> getFilms(){
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(films));
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл с фильмами");
            return null;
        }
        HashMap<Integer,String> mapOfFilms = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] strFilms = scanner.nextLine().split(",");
            mapOfFilms.put(Integer.parseInt(strFilms[0]), strFilms[1]);
        }
        return mapOfFilms;
    }

    public static ArrayList<String> getHistoryOfViews(){
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(historyOfViews));
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл с историей просмотров");
            return null;
        }
        ArrayList<String> arrayViews = new ArrayList<>();
        while (scanner.hasNextLine()) {
            arrayViews.add(scanner.nextLine());
        }
        return arrayViews;
    }

}
