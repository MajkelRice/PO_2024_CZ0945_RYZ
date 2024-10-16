package agh.ics.oop;

public class World {
    public static void  main(String[] args) {
        System.out.println("System Wystartował");

        run(args);
        System.out.println("System zakończył działanie");
    }

     private static void run(String[] args){
        for(String arg : args){
            String msg = switch (arg){
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak skręca w prawo";
                case "l" -> "Zwierzak skręca w lewo";
                default -> "Nieznany kierunek";
            };
            System.out.println(msg);
        }
    }
}
