package ru.croc.task4;

public class Main {


    public static void main(String[] args) {

        Figure circle = new Circle(1,1,5 );
        Figure rectangle = new Rectangle(100,100,150,200);
        Annotation a = new Annotation(circle, "tree");
        System.out.println(a);
        Annotation b = new Annotation(rectangle, "car");
        System.out.println(b);


    }
}