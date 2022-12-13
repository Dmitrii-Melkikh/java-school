package ru.croc.task18;

public class ProductAlreadyExist extends Exception {
    @Override
    public String getMessage() {
        return "Продукт с этим артикулом уже существует";
    }
}
