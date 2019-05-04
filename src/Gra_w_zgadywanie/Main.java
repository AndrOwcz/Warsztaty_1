package Gra_w_zgadywanie;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Gra w zgadywanie liczb");

        Random random = new Random();
        int number = random.nextInt(100) + 1;

        System.out.println("Zgadnij liczbę: ");

        int guessedNumber = readData();

        for (; ; ) {

            if (number > guessedNumber) {
                System.out.println("Za mało!");
                guessedNumber = readData();
            } else if (number < guessedNumber) {
                System.out.println("Za dużo!");
                guessedNumber = readData();
            } else {
                System.out.println("Zgadłeś !!!");
                break;
            }
        }
    }

    private static int readData() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("To nie jest liczba! Podaj liczbę: ");
        }
        return scanner.nextInt();
    }

}
