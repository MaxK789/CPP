package Part3;

import java.util.Random;
import java.util.Scanner;

public class WordPattern {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = {"dog", "cat", "fish"};
        System.out.print("Список слів: ");
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();

        String s = generateRandomString(words);
        System.out.println("s: " + s);

        System.out.print("pattern: ");
        String pattern = scanner.nextLine();

        boolean matches = wordPatternMatch(pattern, s);

        System.out.println("Result: " + matches);
    }

    private static String generateRandomString(String[] words) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int length = 4;

        for (int i = 0; i < length; i++) {
            sb.append(words[random.nextInt(words.length)]);
            if (i < length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static boolean wordPatternMatch(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        String[] patternToWord = new String[26];
        String[] wordToPattern = new String[words.length];

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            int patternIndex = c - 'a';

            if (patternToWord[patternIndex] == null && !contains(wordToPattern, word)) {
                patternToWord[patternIndex] = word;
                wordToPattern[i] = word;
            } else if (!word.equals(patternToWord[patternIndex])) {
                return false;
            }
        }

        return true;
    }

    private static boolean contains(String[] array, String word) {
        for (String s : array) {
            if (word.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
