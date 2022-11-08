package ru.croc.task5;



public class Circle extends Figure {
    double x0, y0, r;
    public Circle(double x0, double y0, double r){
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }

    @Override
    public void move(double dx, double dy){
        this.x0 += dx;
        this.y0 += dy;
    }

    @Override
    public boolean checkPoint(double x, double y){
        double line = Math.sqrt(Math.pow(x-this.x0,2)+Math.pow(y-this.y0,2));
        if (line<=this.r){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
        return "Circle (" + this.x0 + ", " + this.y0 + "),  " +
                this.r + " : ";
    }
}

