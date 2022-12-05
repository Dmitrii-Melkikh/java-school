package ru.croc.task15;

public class Respondent {
    private Integer age;
    private String fullname;

    public Respondent(String fullname, int age){
        this.fullname = fullname;
        this.age = age;
    }

    @Override
    public String toString(){
        return fullname + " (" + age + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Respondent))
            return false;
        return age.equals(((Respondent) obj).age) && fullname.equals(((Respondent) obj).fullname);
    }

    public int getAge() {
        return age;
    }

    public String getFullname() {
        return fullname;
    }
}
