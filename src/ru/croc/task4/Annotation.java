package ru.croc.task4;

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
}
