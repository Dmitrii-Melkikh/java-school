package ru.croc.task17;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\Дмитрий\\Desktop\\Java курс\\java-school\\src\\ru\\croc\\task17\\orders.CSV";
        ImporterToDatabase importerToDatabase = new ImporterToDatabase( path,
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1234",
                "org.postgresql.Driver");
        importerToDatabase.startImport();
    }
}
