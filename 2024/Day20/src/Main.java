import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> race = new ArrayList<>();
        File file = new File("2024//Day20//Data//TestRaceTrack");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                char[] line = sc.nextLine().toCharArray();
                List<Character> raceLine = new ArrayList<>();
                for (char track : line) {
                    raceLine.add(track);
                }
                race.add(raceLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}