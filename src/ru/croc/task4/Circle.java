package ru.croc.task4;

public class Circle extends Figure{
    double x0, y0, r;
    public Circle(double x0, double y0, double r){
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }
    @Override
    public String toString(){
        return "Circle (" + this.x0 + ", " + this.y0 + "),  " +
                this.r + " : ";
    }
}

