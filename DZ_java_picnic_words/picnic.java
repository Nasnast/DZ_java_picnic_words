
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class picnic {
    public static void main(String[] args) throws IOException {

        File file = new File("input.txt");

        wordsCount(file); // количество слов в файле

        findLongestWords(file); // самое длинное слово в файле

        countWords(file); // сколько раз каждое слово встречается в файле

    }

    private static void wordsCount(File file) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            int count = 0;
            while (sc.hasNext()) {
                sc.next();
                count++;
            }
            sc.close();
            System.out.println("Количество слов: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findLongestWords(File file) throws FileNotFoundException {
        String longest_word = "";
        String current;
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                current = sc.next();
                if (current.length() > longest_word.length()) {
                    longest_word = current;
                }
            }
            sc.close();
            System.out.println("Самое длинное слово в файле: " + longest_word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countWords(File file) throws FileNotFoundException {
        Map<String, Integer> words = new HashMap<String, Integer>();
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                String word = sc.next();
                Integer count = words.get(word);
                if (count != null)
                    count++;
                else
                    count = 1;
                words.put(word, count);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sortValue(words); // Сортировка по значениям
    }

    private static void sortValue(Map<String, Integer> words) {
        List<Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(Entry.comparingByValue());

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
