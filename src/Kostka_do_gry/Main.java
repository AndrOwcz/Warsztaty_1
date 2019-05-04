package Kostka_do_gry;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Wprowadź kod do rzucenia kostki w formacie: xDy+z, gdzie: ");
        System.out.println("x - liczba rzutów kośćmi");
        System.out.println("y – rodzaj kostki, którą należy użyć");
        System.out.println("z – (opcjonalna) liczba, którą należy dodać (lub odjąć) do wyniku rzutów");
        String line = readData();

        String[] splittedInputArr = line.split("D");

        int numberOfThrows;
        int add;
        int subtract;
        int diceType;
        int result;

        if (splittedInputArr[0].length() == 0) {
            numberOfThrows = 1;
        } else {
            numberOfThrows = Integer.parseInt(splittedInputArr[0]);
        }

        try {
            if (splittedInputArr[1].contains("+")) {
                String[] arrayIfPlus = splittedInputArr[1].split("[+]");
                add = Integer.parseInt(arrayIfPlus[1]);
                diceType = Integer.parseInt(arrayIfPlus[0]);
                int sumOfDiceThrows = smallSum(diceType, numberOfThrows);
                result = sumOfDiceThrows + add;
                System.out.println("Wynik rzutu kostką:\n"
                        + numberOfThrows + "*D" + diceType + "+" + add + "=" + result);

            } else if (splittedInputArr[1].contains("-")) {
                String[] arrayIfPlus = splittedInputArr[1].split("-");
                subtract = Integer.parseInt(arrayIfPlus[1]);
                diceType = Integer.parseInt(arrayIfPlus[0]);
                int sumOfDiceThrows = smallSum(diceType, numberOfThrows);
                result = sumOfDiceThrows - subtract;
                System.out.println("Wynik rzutu kostką:\n"
                        + numberOfThrows + "*D" + diceType + "-" + subtract + "=" + result);
            } else {
                diceType = Integer.parseInt(splittedInputArr[1]);
                result = smallSum(diceType, numberOfThrows);
                System.out.println("Wynik rzutu kostką:\n"
                        + numberOfThrows + "*D" + diceType + "=" + result);
            }
        } catch (NumberFormatException e) {
            System.out.println("Błędnie wprowadzony kod. Wprowadź go w formacie: xDy+z, gdzie: ");
            System.out.println("x - liczba rzutów kośćmi");
            System.out.println("y – rodzaj kostki, którą należy użyć");
            System.out.println("z – (opcjonalna) liczba, którą należy dodać (lub odjąć) do wyniku rzutów");
        }
    }

    private static String readData() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        for (; ; ) {
            if (!line.contains("D")) {
                System.out.println("Błąd w kodzie, wprowadź kod ponownie");
                line = scanner.nextLine();
            } else {
                break;
            }
        }
        return line;
    }

    private static int smallSum(int diceType, int numberOfThrows) {
        int sumOfDiceThrows = 0;
        Random random = new Random();
        int[] throwArr = new int[numberOfThrows];
        for (int i = 0; i < numberOfThrows; i++) {
            throwArr[i] = random.nextInt(diceType) + 1;
            sumOfDiceThrows += throwArr[i];
        }
        return sumOfDiceThrows;
    }
}
