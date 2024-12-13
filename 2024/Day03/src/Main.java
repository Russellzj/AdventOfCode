import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        File corruptData = new File("2024//Day03//Data//input");
        StringBuilder corrupt = new StringBuilder();
        try (Scanner sc = new Scanner(corruptData)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                corrupt.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(corrupt);
    }
}