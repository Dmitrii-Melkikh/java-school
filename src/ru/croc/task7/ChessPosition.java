package ru.croc.task7;

public class ChessPosition {
    int x,y;

    public ChessPosition(int x, int y) throws IllegalPositionException{
        if (x >= 0 && x < 8 && y >= 0 && y <  8) {
            this.x = x;
            this.y = y;
        }
        else {
            throw new IllegalPositionException(x, y);
        }

    }

    static ChessPosition parse(String position) throws IllegalPositionException {
        if (position.length()>2){
            throw new IllegalPositionException(-1, -1);
        }
        int x = position.charAt(0) - 'a';
        int y = position.charAt(1) - '0' - 1;

        return new ChessPosition(x, y);
    }




    @Override
    public String toString(){
        String col = new String();
        switch (x) {
            case 0 -> col = "a";
            case 1 -> col = "b";
            case 2 -> col = "c";
            case 3 -> col = "d";
            case 4 -> col = "e";
            case 5 -> col = "f";
            case 6 -> col = "g";
            case 7 -> col = "h";
        }
        return col + (y+1);
    }




}
