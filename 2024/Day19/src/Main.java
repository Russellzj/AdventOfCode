import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Towel towels = new Towel();

        File file = new File("2024/Day19/Data/TestTowelDesigns");
        List<String> patterns = new ArrayList<>();
        try (Scanner sc = new Scanner(file);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) break;
                for (String towel : line.split(", ")) {
                    towels.addTowel(towel);
                }
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                patterns.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int possiblePatterns = 0;
        for (String pattern : patterns) {
            System.out.print(pattern);
            System.out.println(": Pattern can be created: " + towels.findPatter(pattern));
            if (towels.findPatter(pattern)) {
                possiblePatterns++;
            }
        }
        System.out.println("Possible Patterns: " + possiblePatterns);
        System.out.println("END");
    }
}