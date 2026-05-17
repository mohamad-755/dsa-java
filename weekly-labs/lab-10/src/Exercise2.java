import java.io.*;
import java.util.*;

public class Exercise2 {

    public static Map<String, Integer> buildWordCount(Scanner input) {
        Map<String, Integer> r = new HashMap<>();
        while (input.hasNext()) {
            String word = input.next();
            if (r.containsKey(word)) {
                int count = r.get(word);
                r.put(word, count + 1);
            } else {
                r.put(word, 1);
            }
        }
        return r;
    }

    public static void printRepeatedWords(Map<String, Integer> map) {
        for (String n : map.keySet()) {
            if (map.get(n) > 1) {
                System.out.println(n);
            }
        }
    }

    public static String mostFrequentWord(Map<String, Integer> map) {
        String q = "";
        int largest = 0;
        for (String n : map.keySet()) {
            if (map.get(n) > largest) {
                largest = map.get(n);
                q = n;
            }
        }
        return q;
    }

    public static int totalDistinctWords(Map<String, Integer> map) {

        return map.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("words.txt"));

        Map<String, Integer> wordCount = buildWordCount(input);

        System.out.println(wordCount);
        printRepeatedWords(wordCount);
        System.out.println(mostFrequentWord(wordCount));
        System.out.println(totalDistinctWords(wordCount));
    }
}