package ru.croc.task6;

public class Main {

    public static String removeJavaComments(String source){
        String[] tokens = source.split("//.*|(\\\"(?:\\\\\\\\[^\\\"]|\\\\\\\\\\\"|.)*?\\\")|(?s)/\\\\*.*?\\\\*/");
        return String.join("", tokens);
    }

    public static void main(String[] args){
        String source = """
                /*
                * My first ever program in Java!
                */
                class Hello { // class body starts here 
                    /* main method */
                    public static void main(String[] args/* we put command line arguments here*/) {
                        // this line prints my first greeting to the screen
                        System.out.println("Hi!"); // :)
                    }
                } // the end
                // to be continued...
                """;
        String noComments = removeJavaComments(source);
        System.out.println(noComments);


    }
}
