package Symulator_Lotto;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Losowanie LOTTO");

        int[] numbers = readData();

        System.out.println();

        System.out.println("Wprowadzone liczby to: ");
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        Collections.shuffle(Arrays.asList(arr));

        System.out.println("Wylosowane liczby to: ");
        int[] drawnArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            drawnArray[i] = arr[i];
        }
        Arrays.sort(drawnArray);
        System.out.println(Arrays.toString(drawnArray));

        System.out.println();

        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i] == drawnArray[j]) {
                    count++;
                }
            }
        }
        System.out.println("Wynik loterii lotto: ");
        switch (count) {
            case 0:
                System.out.println("Zero trafień");
                break;
            case 1:
                System.out.println("Jedno trafienie");
                break;
            case 2:
                System.out.println("Dwa trafienia");
                break;
            case 3:
                System.out.println("Trafiłeś trójkę! gratulacje!");
                break;
            case 4:
                System.out.println("Trafiłeś czwórkę!! gratulacje!");
                break;
            case 5:
                System.out.println("Trafiłeś piątkę!!! gratulacje!");
                break;
            case 6:
                System.out.println("Szóstka!!! KUMULACJA !!!");
                break;
        }
    }

    private static int[] readData() {
        Scanner scanner = new Scanner(System.in);
        int[] number = new int[6];

        for (int i = 0; i < number.length; ) {
            System.out.println("Wpisz " + (i + 1) + " liczbę: ");

            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("To nie jest liczba! Podaj " + (i + 1) + " liczbę: ");
            }
            int drawnNumber = scanner.nextInt();
            if (drawnNumber >= 1 && drawnNumber <= 49 && !arrayContainsNumber(number, drawnNumber)) {

                number[i] = drawnNumber;
                i++;

            } else {
                System.out.println("Błędna liczba, wprowadz taką, której jeszcze nie było");
                System.out.println("i w poprawnym zakresie!");
            }
        }
        return number;
    }

    private static boolean arrayContainsNumber(int[] array, int number) {

        for (int n : array) {
            if (n == number) {
                return true;
            }
        }
        return false;
    }
}
