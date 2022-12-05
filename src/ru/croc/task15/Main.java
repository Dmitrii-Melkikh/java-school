package ru.croc.task15;

import java.util.Scanner;
import java.util.*;


public class Main {



    public static void main(String[] args) {

        int[] ageGroups = new int[args.length];
        for (int i = 0; i < ageGroups.length; i++) {
            ageGroups[i] = Integer.parseInt(args[i]);
        }

        ArrayList<Respondent> respondents = new ArrayList<>();
        System.out.println("Введите респондентов: ");
        inputRespondents(respondents);
        distributionByGroup(ageGroups, respondents);

    }

    private static void inputRespondents(ArrayList<Respondent> respondents){
        Scanner sc = new Scanner(System.in);
        while (true){
            String input = sc.nextLine();
            String fullname = "";
            int age;
            if (input.equals("END")){
                break;
            }
            else{
                String[] words = input.split(" ");
                fullname += words[0] + " " + words[1] + " " + words[2].replace(",","");
                age = Integer.parseInt(words[3]);
            }
            if (age > 123 || age < 0){
                System.out.println("Некорректно введен возраст, попробуйте еще раз");
            }
            else {
                Respondent respondent = new Respondent(fullname, age);
                for (Respondent r : respondents) {
                    if (r.equals(respondent)) {
                        System.out.println("Не может встретиться двух респондентов однофамильцев с одинаковыми возрастами. " +
                                "Попробуйте ещё раз");
                    }
                }
                respondents.add(respondent);
            }
        }
    }

    private static void distributionByGroup(int[] ageGroups, ArrayList<Respondent> respondents){

        for (int i = ageGroups.length - 1; i >= 0; i--) {

            ArrayList<Respondent> respondentsInGroup = new ArrayList<>();
            ArrayList<Respondent> respondentsRemove = new ArrayList<>();

            for (Respondent respondent: respondents){
                if (respondent.getAge() > ageGroups[i] ) {
                    respondentsInGroup.add(respondent);
                    respondentsRemove.add(respondent);
                }
            }
            respondents.removeAll(respondentsRemove);

            if (respondentsInGroup.size() != 0) {
                respondentsInGroup.sort(new Comparator<>() {
                    @Override
                    public int compare(Respondent r1, Respondent r2) {
                        if (r1.getAge() != r2.getAge()) {
                            return (-Integer.compare(r1.getAge(), r2.getAge()));
                        } else {
                            for (int i = 0; i < r1.getFullname().length(); i++) {
                                if (r1.getFullname().charAt(i) != r2.getFullname().charAt(i))
                                    return (Character.compare(r1.getFullname().charAt(i), r2.getFullname().charAt(i)));
                            }
                        }
                        return 0;
                    }
                });

                String resRespondents = respondentsInGroup.toString().replace("[", "").replace("]", "");
                if (i != ageGroups.length - 1){
                    System.out.println((ageGroups[i] + 1) + "-" + ageGroups[i + 1] + ": " + resRespondents);
                }
                else {
                    System.out.println((ageGroups[i] + 1) + "+: " + resRespondents);
                }
            }
            if (i == 0 && respondents.size() > 0){
                ArrayList<Respondent> lastGroup = new ArrayList<>();
                lastGroup.addAll(respondents);
                lastGroup.sort(new Comparator<>() {
                    @Override
                    public int compare(Respondent r1, Respondent r2) {
                        if (r1.getAge() != r2.getAge()) {
                            return (-Integer.compare(r1.getAge(), r2.getAge()));
                        } else {
                            for (int i = 0; i < r1.getFullname().length(); i++) {
                                if (r1.getFullname().charAt(i) != r2.getFullname().charAt(i))
                                    return (Character.compare(r1.getFullname().charAt(i), r2.getFullname().charAt(i)));
                            }
                        }
                        return 0;
                    }
                });
                System.out.print("0-" + ageGroups[0] + ": " + lastGroup.toString().replace("[", "").replace("]", ""));
            }
        }
    }
}
