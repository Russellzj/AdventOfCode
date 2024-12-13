import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Imports File and adds data to ArrayList
        File wordSearchFile = new File("2024/Day04/Data/WordSearch");
        List<char[]> wordSearch = new ArrayList<>();
        try(Scanner sc = new Scanner(wordSearchFile)) {
            while(sc.hasNextLine()) {
                List<Character> line = new ArrayList<>();
                wordSearch.add(sc.nextLine().toCharArray());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(findWord(wordSearch, "XMAS"));
        System.out.println(findShape(wordSearch, "MAS"));
    }
    //PART 02
    public static int findShape(List<char[]> wordSearch, String word) {
        int count = 0;
        String wordR = "";
        for (char c : word.toCharArray()) {
            wordR = c + wordR;
        }
        int[][]coordsA = {{0, 0}, {1, 1}, {2, 2}};
        int[][]coordsB = {{0, 2}, {1, 1}, {2, 0}};
        for (int i = 0; i < wordSearch.size(); i++) {
            for (int j = 0; j < wordSearch.get(i).length; j++) {
                if (i + word.length() - 1 < wordSearch.get(i).length && j + word.length() - 1 < wordSearch.get(i + 2).length) {
                    String wordCheckA = "";
                    for (int[] coords : coordsA) {
                        wordCheckA += String.valueOf(wordSearch.get(i + coords[0])[coords[1] + j]);
                    }
                    String wordCheckB = "";
                    for (int[] coords : coordsB) {
                        wordCheckB += String.valueOf(wordSearch.get(i + coords[0])[coords[1] + j]);
                    }
                    if (wordCheckA.equals(word) || wordCheckA.equals(wordR))
                        if (wordCheckB.equals(word) || wordCheckB.equals(wordR)) {
                            count++;
                        }
                }
            }
        }
        return count;
    }

    //PART 01
    public static int findWord(List<char[]> wordSearch, String word) {
        char[] wordToFind= word.toCharArray();
        int wordCount = 0;
        //Traverses All values looking for X
        for (int i = 0; i < wordSearch.size(); i++) {
            for (int j = 0; j < wordSearch.get(i).length; j++) {
                if (wordSearch.get(i)[j] == wordToFind[0]) {
                    //IF X is found check surrounding values for M
                    for (int k = -1 ; k <= 1 ; k++) {
                        for (int l = -1 ; l <= 1 ; l++) {
                            if (k + i >= 0 && l + j >= 0) {
                                if (k + i < wordSearch.size() && l + j < wordSearch.get(i).length) {
                                    if (wordSearch.get(i + k)[j + l] == wordToFind[1]) {
                                        if (isWordInDirection(wordSearch, i + k, j + l, wordToFind, 2, k, l)) {
                                            wordCount++;
                                            System.out.println("Match at X: " + i + " " + j);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return wordCount;
    }

    public static boolean isWordInDirection(List<char[]> wordSearch, int x, int y, char[] word,
                                            int letter, int dx, int dy) {
        int nextX = x + dx ;
        if (nextX < wordSearch.size() && nextX >= 0) {
            int nextY = y + dy;
            if (nextY < wordSearch.get(nextX).length && nextY >= 0) {
                if(wordSearch.get(nextX)[nextY] == word[letter]) {
                    if (letter >= word.length - 1) {
                        return true;
                    } else {
                        return isWordInDirection(wordSearch, nextX, nextY, word, letter + 1, dx, dy);
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }


}