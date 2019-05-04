package Wyszuk_najpopular_slow;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        getData("http://www.onet.pl/");
        // downloads data from website
        createFilteredFile();
        // creates file: filtered_popular_words.txt
        findFrequency();
        // creates file: FRQ_filtered_popular_words.txt
        // creates file: FRQ_filtered_popular_words_counted.txt
    }

    public static void getData(String url) {
        Connection connect = Jsoup.connect(url);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                stringBuilder.append(elem.text() + " ");
            }
//            System.out.println(stringBuilder);

            String[] wordsTable = stringBuilder.toString().replaceAll("\\p{P}", "")
                    .toLowerCase().split(" ");
//            System.out.println(Arrays.toString(wordsTable));
            String popularFileName = "popular_words.txt";
            Path filePath = Paths.get(popularFileName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Stworzono plik");
            } else {
                System.out.println("Plik " + popularFileName + " istnieje - nadpisano");
            }
            List<String> outList = new ArrayList<>();
            for (String line : wordsTable) {
                if (line.length() > 2) {
                    outList.add(line);
                }
            }
            Files.write(filePath, outList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFilteredFile() {

        try {
            Path filePathExcluded = Paths.get("excluded_words.txt");
            Path filePath = Paths.get("popular_words.txt");
            if (Files.exists(filePath)) {
                String newFileName = "filtered_popular_words.txt";
                Path pathToWrite = Paths.get(newFileName);
                List<String> loadedExcludedLines = Files.readAllLines(filePathExcluded);
                List<String> loadedLines = Files.readAllLines(filePath);
                List<String> filtered = new ArrayList<>();

                for (String line : loadedLines) {
                    if (!loadedExcludedLines.contains(line))
                        filtered.add(line);
                }

                Files.write(pathToWrite, filtered);
                System.out.println("zapisano pomyślnie plik " + newFileName + ", z wyłączeniem słow z pliku "
                        + filePathExcluded);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("Nie udało się zapisać pliku");
        }
    }

    private static void findFrequency() {


        try {
            class ValueComparator implements Comparator<String> {
                Map<String, Integer> base;

                public ValueComparator(Map<String, Integer> base) {
                    this.base = base;
                }

                // Note: this comparator imposes orderings that are inconsistent with
                // equals.
                public int compare(String a, String b) {
                    if (base.get(a) >= base.get(b)) {
                        return -1;
                    } else {
                        return 1;
                    } // returning 0 would merge keys
                }
            }

            Path filePathPopular = Paths.get("filtered_popular_words.txt");
            if (Files.exists(filePathPopular)) {
                String newFileName = "FRQ_filtered_popular_words.txt";
                Path pathToWrite = Paths.get(newFileName);
                List<String> loadedLines = Files.readAllLines(filePathPopular);
                Collections.sort(loadedLines);

                Files.write(pathToWrite, loadedLines);
                System.out.println("zapisano pomyślnie plik " + newFileName + ", w którym są najczęściej używane" +
                                " słowa w kolejności alfabetycznej");

                Map<String, Integer> occurrences = new HashMap<String, Integer>();

                for (String word : loadedLines) {
                    Integer oldCount = occurrences.get(word);
                    if (oldCount == null) {
                        oldCount = 0;
                    }
                    occurrences.put(word, oldCount + 1);
                }

                ValueComparator bvc = new ValueComparator(occurrences);
                TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
                sorted_map.putAll(occurrences);
//                System.out.println("results: " + sorted_map);

                String countedPopularFileName = "FRQ_filtered_popular_words_counted.txt";
                int count = 0;
                FileWriter fstream = new FileWriter(countedPopularFileName);
                BufferedWriter out = new BufferedWriter(fstream);

                Iterator<Map.Entry<String, Integer>> it = sorted_map.entrySet().iterator();
                while (it.hasNext() && count < sorted_map.size()) {

                    Map.Entry<String, Integer> pairs = it.next();

                    // since you only want the value, we only care about pairs.getValue(), which is written to out
                    out.write(pairs.getKey() + " " + pairs.getValue() + "\n");

                    // increment the record count once we have printed to the file
                    count++;
                }
                // close the file and end
                out.close();
                System.out.println("zapisano pomyślnie plik " + countedPopularFileName + ", w którym są najczęściej używane " +
                        "słowa w kolejności alfabetycznej, a także ilość ich występowań na danej stronie");
            }

        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("Nie udało się");
        }


    }
}