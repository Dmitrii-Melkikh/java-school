package ru.croc.task16;

import ru.croc.task15.Respondent;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Driver> drivers = new ArrayList<>();
        System.out.println("Введите координаты (широта и долгота): ");
        String[] coordinates = sc.nextLine().split(" ");
        double latitude = Double.parseDouble(coordinates[0].replace(",",""));
        double longtitude = Double.parseDouble(coordinates[1]);
        System.out.println("Введите класс комфорта: ");
        String comfortClass = sc.nextLine();
        Set<String> specialRequests = new HashSet<>();
        System.out.println("Введите особые пожелания (каждое на новой строчке) или END для окончания ввода: ");
        String request = sc.nextLine();
        while(!request.equals("END")){
            specialRequests.add(request);
            request = sc.nextLine();
        }
        Set<String> h = new HashSet<>(Arrays.asList("Детское кресло", "Кондиционер"));
        Set<String> h1 = new HashSet<>(Arrays.asList("Кондиционер"));
        Set<String> h2 = new HashSet<>(Arrays.asList("Детское кресло", "Багаж"));
        drivers.add(new Driver(59,63, "Комфорт", h, "U-SnezhanaDenisovna-79995553535"));
        drivers.add(new Driver(59,57, "Комфорт", h1, "K-OleqIgorevich-79371283511"));
        drivers.add(new Driver(78,37, "Комфорт", h2, "L-NikitaLeonidovich-79510310341"));
        drivers.sort(new Comparator<>() {
            @Override
            public int compare(Driver d1, Driver d2) {
                if (d1.getComfortClass().equals(comfortClass) && !d2.getComfortClass().equals(comfortClass)) {
                    return -1;
                }
                else if(!d1.getComfortClass().equals(comfortClass) && d2.getComfortClass().equals(comfortClass)){
                    return 1;
                }
                if (d1.getSpecialRequests().containsAll(specialRequests) && !d2.getSpecialRequests().containsAll(specialRequests)){
                    return -1;
                }
                else if (!d1.getSpecialRequests().containsAll(specialRequests) && d2.getSpecialRequests().containsAll(specialRequests)){
                    return 1;
                }
                if (distance_Between_LatLong(latitude, longtitude, d1.getLatitude(), d1.getLongitude()) >
                        distance_Between_LatLong(latitude, longtitude, d2.getLatitude(), d2.getLongitude())){
                    return 1;
                }
                else if (distance_Between_LatLong(latitude, longtitude, d1.getLatitude(), d1.getLongitude()) <
                        distance_Between_LatLong(latitude, longtitude, d2.getLatitude(), d2.getLongitude())){
                    return -1;
                }
                else{
                    return 0;
                }


            }
        });
        System.out.println(drivers.get(0).getId());
        System.out.println(drivers);

    }

    public static double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double earthRadius = 6371.01;
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }
}
