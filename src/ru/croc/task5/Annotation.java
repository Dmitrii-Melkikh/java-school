package ru.croc.task5;


public class Annotation {
    public Figure figure;
    public String label;

    public Annotation(Figure figure, String label) {
        this.figure = figure;
        this.label = label;
    }

    @Override
    public String toString() {
        return this.figure + this.label;
    }

    public boolean checkPoint(double x, double y) {
        return this.figure.checkPoint(x, y);
    }

    public boolean checkLabel(String label){
        return this.label.contains(label);
    }

}
