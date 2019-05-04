package Gra_w_zgadywanie_2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Kolejna gra w zgadywanie liczb");

        System.out.println("Pomyśl liczbę od 0 do 1000, a ja zgadnę w maksymalnie 10 próbach");

        int min = 0;
        int max = 1001;

        for (; ; ) {
            int guess = (max - min) / 2 + min;
            System.out.println("Zgaduję: " + guess);

            String answer = readData();

            if (answer.equals("z")) {
                System.out.println("Wygrałem!!!");
                break;
            } else {
                switch (answer) {
                    case "w":
                        min = guess;
                        break;
                    case "m":
                        max = guess;
                        break;
                }
            }
        }
    }

    private static String readData() {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();


        for (; ; ) {
            if (line.equals("w") || line.equals("m") || line.equals("z")) {
                break;
            } else {
                System.out.println("nie oszukuj!!!");
                System.out.println("napisz:" + "\n" + "w, jeśli Twoja liczba jest większa" + "\n" +
                        "m, jeśli Twoja liczba jest mniejsza" + "\n" + "lub z, jeśli zgadłem");
                line = scanner.nextLine();
            }
        }
        return line;
    }
}
