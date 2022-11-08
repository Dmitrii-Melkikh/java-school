package ru.croc.task5;



public class Main {

    public static void main(String[] args) {

        Figure circle = new Circle(1,1,5 );
        // смещение фигуры
        circle.move(-1,-1);
        Figure rectangle = new Rectangle(100,100,150,200);
        Annotation a = new Annotation(circle, "tree");
        System.out.println(a);
        Annotation b = new Annotation(rectangle, "car");
        System.out.println(b);
        Annotation[] annotations = new Annotation[2];
        annotations[0] = a;
        annotations[1] = b;
        AnnotatedImage res = new AnnotatedImage("image/path", annotations);
        System.out.println("Поиск по подписи \"car\":\n" + res.findByLabel("car"));
        System.out.println("Поиск по точке (2,3):\n" + res.findByPoint(2,3));



    }
}
