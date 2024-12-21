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

        int regexPattern = 0;
        for (String pattern : patterns) {
            if (towels.findRegex(pattern)) {
                regexPattern++;
            }
        }
        System.out.println("Acceptable patterns using regex: " + regexPattern);

        /*
        int possiblePatterns = 0;
        for (String pattern : patterns) {
            //System.out.print(pattern);
            //System.out.println(": Pattern can be created: " + towels.findRegex(pattern));
            if (towels.findDesign(pattern)) {
                possiblePatterns++;
            }
        }

         */

        System.out.println("END");
    }
}