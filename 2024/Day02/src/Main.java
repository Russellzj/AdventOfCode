import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File locations = new File("2024//Day02//Data//RedReactor");
        List<List<Integer>> redReactor = new ArrayList<>();
        try (Scanner sc = new Scanner(locations)){
            while (sc.hasNextLine()) {
                List<Integer> report = new ArrayList<>();
                String[] values = sc.nextLine().split(" ");
                for (String value : values) {
                    report.add(Integer.parseInt(value));
                }
                redReactor.add(report);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(redReactor.size());
    }
}