package ru.croc.task7;

import java.util.Scanner;

public class Main {


    public static boolean checkPairs(ChessPosition first, ChessPosition second) throws IllegalMoveException{
        if((Math.abs(first.x - second.x) == 1 && Math.abs(first.y - second.y) == 2 ) ||
                (Math.abs(first.x - second.x) == 2 && Math.abs(first.y - second.y) == 1)) {
            return true;
        }
        else
            throw new IllegalMoveException(first, second);

    }


    public static String checkMove(ChessPosition[] positions) throws IllegalMoveException{
        for (int i = 0; i < positions.length - 1; i++){
            if (checkPairs(positions[i],positions[i+1])){
                continue;
            }
        }
        return "OK";
    }

    public static void main(String[] args) {
        try{
//            ChessPosition a = ChessPosition.parse("g8");
//            ChessPosition b = ChessPosition.parse("e7");
//            ChessPosition c = ChessPosition.parse("c8");
//            ChessPosition[] positions = new ChessPosition[3];
//            positions[0] = a;
//            positions[1] = b;
//            positions[2] = c;
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите позиции на доске: ");
            String inp = sc.nextLine();
            String[] argums = inp.split(" ");
            ChessPosition[] positions = new ChessPosition[argums.length];
            for (int i = 0; i < argums.length; i++) {
                positions[i] = ChessPosition.parse(argums[i]);
            }
            System.out.println(checkMove(positions));
        }
        catch (IllegalPositionException e){
            System.out.println(e.getMessage());
        }
        catch (IllegalMoveException e){
            System.out.println(e.getMessage());
        }

    }
}
