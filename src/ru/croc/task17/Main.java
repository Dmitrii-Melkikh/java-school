package ru.croc.task17;

public class Main {
    public static void main(String[] args) {
        ImporterToDatabase importerToDatabase = new ImporterToDatabase( args[0],
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1234",
                "org.postgresql.Driver");
        importerToDatabase.startImport();
    }
}
