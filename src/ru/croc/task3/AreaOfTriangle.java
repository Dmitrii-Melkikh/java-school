package ru.croc.task3;

public class AreaOfTriangle {

    static class Point{
        double x;
        double y;
    }

    public static void main(String[] args) {



        Point a = new Point();
        a.x = Double.parseDouble(args[0]);
        a.y = Double.parseDouble(args[1]);

        Point b = new Point();
        b.x = Double.parseDouble(args[2]);
        b.y = Double.parseDouble(args[3]);

        Point c = new Point();
        c.x = Double.parseDouble(args[4]);
        c.y = Double.parseDouble(args[5]);

        double S;
        S = Math.abs((b.x-a.x)*(c.y-a.y)-(c.x-a.x)*(b.y-a.y))/2;
        /*
        Если кириллица отоброжается некорректно:
        String yourEncoding = "Windows-1251";
        String outString ="Площадь треугольника: " + S;
        byte firstBytes[] = outString.getBytes(yourEncoding);
        String value = new String(firstBytes, "UTF-8");
        */
        System.out.println("Площадь треугольника: " + S);

    }

}
