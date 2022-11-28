package ru.croc.task13;

import java.util.*;

public class Recommendation {
    public static String findRecommendation(String userFilms){
        HashMap<Integer,String> mapOfFilms = GetInfo.getFilms();
        ArrayList<String> arrayViews = GetInfo.getHistoryOfViews();
        ArrayList<HashSet<Integer>> setOfViews = new ArrayList<>();

        for(String view: arrayViews) {
            HashSet<Integer> views = new HashSet<>();
            String[] numbers = view.split(",");
            for(String number:numbers) {
                views.add(Integer.parseInt(number));
            }
            setOfViews.add(views);
        }

        String[] films = userFilms.split(",");
        HashSet<Integer> setOfUserFilms = new HashSet<>();
        for (String film: films) {
            setOfUserFilms.add(Integer.parseInt(film));
        }
        HashSet<Integer> validFilms = getValidFilms(setOfUserFilms, setOfViews);

        int idRecommendFilm = getRecommendFilm(validFilms, setOfViews);
        if (idRecommendFilm!=-1){
            return mapOfFilms.get(idRecommendFilm);
        }
        else{
            return "There is not recommendation for you";
        }

    }

    private static HashSet<Integer> getValidFilms(HashSet<Integer> setOfUserFilms, ArrayList<HashSet<Integer>> listOfViews) {
        HashSet<Integer> validFilms = new HashSet<>();
        for (HashSet<Integer> view : listOfViews) {
            HashSet<Integer> temp = new HashSet<>(view);
            temp.removeAll(setOfUserFilms);
            if (temp.size() <= (view.size() / 2 )) {
                validFilms.addAll(temp);
            }
        }
        return validFilms;
    }



    private static int getRecommendFilm(HashSet<Integer> validFilms, ArrayList<HashSet<Integer>> setOfViews){
        int count, id = -1, max = 0;
        HashSet<Integer> allRecommendFilms= new HashSet<>();
        for(Integer film: validFilms){
            count = 0;
            for (HashSet<Integer> view: setOfViews){
                if(view.contains(film)){
                    count++;
                }
            }
            if (count > max){
                id = film;
            }
        }
        return id;

    }
}
