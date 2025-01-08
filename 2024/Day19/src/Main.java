import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Towel towels = new Towel();

        File file = new File("2024/Day19/Data/TowelDesigns");
        List<String> designs = new ArrayList<>();
        List<String> patterns = new ArrayList<>();
        try (Scanner sc = new Scanner(file);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) break;
                for (String towel : line.split(", ")) {
                    towels.addTowel(towel);
                    designs.add(towel);
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

        double solutions = 0;
        for (String pattern : patterns) {
            System.out.println(pattern);
            double currentPattern = StringPatternMatcher.countWaysToCreateString(pattern, designs);
            System.out.printf("%.0f\n", currentPattern);
            solutions += currentPattern;

        }
        System.out.printf("Solutions: %.9f", solutions );


        /*
        int possiblePatterns = 0;
        for (String pattern : patterns) {
            System.out.println(pattern);
            int currentPattern = towels.findDesign(pattern);
            System.out.println("Design has " + currentPattern + "many solutions");
            possiblePatterns += currentPattern;

        }



        System.out.println("Possible patterns: " + possiblePatterns);

         */

        System.out.println("END");
    }
}