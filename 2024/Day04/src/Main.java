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

        //Prints WordSearch ArrayList
        for (char[] theLine : wordSearch) {
            System.out.println(theLine);
        }
    }

    public static int findXmas(List<List<Character>> wordSearch) {
        int xmas = 0;
        return 0;
    }
}