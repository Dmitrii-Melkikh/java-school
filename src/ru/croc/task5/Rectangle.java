package ru.croc.task5;


public class Rectangle extends Figure {
    double x1, y1, x2, y2;
    public Rectangle(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void move(double dx, double dy){
        this.x1 += dx;
        this.x2 += dx;
        this.y1 += dy;
        this.y2 +=dy;

    }
    @Override
    public boolean checkPoint(double x, double y){
        return x <= Math.max(this.x1, this.x2) && x >= Math.min(this.x1, this.x2) &&
                y <= Math.max(this.y1, this.y2) && x >= Math.min(this.y1, this.y2);
    }

    @Override
    public String toString(){
        return "Rectangle (" + this.x1 + ", " + this.y1 + "), (" +
                this.x2 + ", " + this.y2 + ") : ";

    }

}
