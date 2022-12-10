package ru.croc.task15;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        ArrayList<AgeGroup> ageGroups = new ArrayList<>();

        ageGroups.add(new AgeGroup(0 , Integer.parseInt(args[0])));
        for (int i = 1; i < args.length; i++){

            ageGroups.add(new AgeGroup(Integer.parseInt(args[i-1]) + 1, Integer.parseInt(args[i])));
        }
        ageGroups.add(new AgeGroup(Integer.parseInt(args[args.length-1]) + 1, 10000));


        ArrayList<Respondent> respondents = new ArrayList<>();
        System.out.println("Введите респондентов: ");
        inputRespondents(respondents);

        for (Respondent respondent: respondents){
            for(AgeGroup ageGroup:ageGroups){
                if (ageGroup.getEndAge() == -1){

                }
                if (ageGroup.getStartAge() <= respondent.getAge() &&
                        respondent.getAge()<= ageGroup.getEndAge()){
                    ageGroup.addRespondent(respondent);
                }
            }
        }
        Collections.reverse(ageGroups);
        for (AgeGroup ageGroup : ageGroups) {
            ageGroup.sortInGroup();
            String resRespondents = ageGroup.getRespondents().toString().replace("[", "").replace("]", "");
            if (ageGroup.getRespondents().size() != 0){
                if (ageGroup.getEndAge() != 10000) {
                    System.out.println(ageGroup.getStartAge() + "-" + ageGroup.getEndAge() + ": "
                            + resRespondents);
                } else {
                    System.out.println(ageGroup.getStartAge() + "+ : "
                            + resRespondents);
                }
            }
        }
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


}
