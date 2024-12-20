import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("2024/Day19/Data/TestTowelDesigns");
        Set<String> towels = new HashSet<>();
        List<String> patterns = new ArrayList<>();
        try (Scanner sc = new Scanner(file);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) break;
                for (String towel : line.split(", ")) {
                    towels.add(towel);
                }
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                patterns.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
}