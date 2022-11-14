package ru.croc.task7;

public class IllegalMoveException extends Exception{

    private final ChessPosition first;
    private final ChessPosition second;

    public IllegalMoveException(ChessPosition first, ChessPosition second){
        this.first = first;
        this.second = second;
    }
    @Override
    public String getMessage(){
        return "Конь так не ходит : " + first + " -> " + second;
    }
}
