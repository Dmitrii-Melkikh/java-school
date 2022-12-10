package ru.croc.task15;

import java.util.*;

public class AgeGroup {

    private ArrayList<Respondent> respondents;
    private int startAge;
    private int endAge;


    public AgeGroup(int startAge, int endAge) {
        this.startAge = startAge;
        this.endAge = endAge;
        this.respondents = new ArrayList<>();
    }

    public void addRespondent(Respondent respondent){
        this.respondents.add(respondent);
    }
    public void sortInGroup() {
        this.respondents.sort((r1, r2) -> {
            if (r1.getAge() != r2.getAge()) {
                return (-Integer.compare(r1.getAge(), r2.getAge()));
            } else {
                if (!r1.getFullname().equals(r2.getFullname()))
                    return r1.getFullname().compareTo(r2.getFullname());
            }
            return 0;
        });
    }

    public ArrayList<Respondent> getRespondents() {
        return respondents;
    }



    public int getStartAge() {
        return startAge;
    }



    public int getEndAge() {
        return endAge;
    }


}