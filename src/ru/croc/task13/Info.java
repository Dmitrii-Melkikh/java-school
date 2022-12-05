package ru.croc.task13;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Info {

    private String films;
    private String historyOfViews;

    public Info(String films, String historyOfViews){
        this.films = films;
        this.historyOfViews = historyOfViews;
    }
    public HashMap<Integer,String> pullOutFilms(){
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

    public ArrayList<String> pullOutHistoryOfViews(){
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
