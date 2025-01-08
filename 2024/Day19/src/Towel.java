import java.util.*;

public class Towel {
    private Set<String> towels = new HashSet<>();
    private int solutions = 0;
    private String regex = "()+";

    public void addTowel(String towel) {
        towels.add(towel);
        if (regex.length() != 3) {
            regex = regex.substring(0, 1) + towel + "|" + regex.substring(1);
        } else {
            regex = regex.substring(0, 1) + towel + regex.substring(1);
        }
    }

    public int findDesign(String pattern) {
        solutions = 0;
        Set<String> currentTowels = new HashSet<>(towels);
        for (int i = 1; i <= pattern.length(); i++) {
            String testPattern = pattern.substring(0, i);
            while (currentTowels.contains(testPattern)) {
                currentTowels.remove(testPattern);
                nextDesign(pattern, i);
            }
        }
        return solutions;
    }

    private void nextDesign(String pattern, int location) {
        Set<String> currentTowels = new HashSet<>(towels);
        for (int i = pattern.length(); i > location; i--) {
            String testPattern = pattern.substring(location, i);
            while (currentTowels.contains(testPattern)) {
                if (pattern.length() == i) {
                    solutions++;
                    if (solutions % 1000000 == 0) {
                        System.out.println("Current Solutions: " + solutions / 1_000_000 + "M");
                    }
                    currentTowels.remove(testPattern);
                }
                else {
                    currentTowels.remove(testPattern);
                    nextDesign(pattern, i);
                }
            }
        }
    }

    public boolean findRegex(String pattern) {
        return pattern.matches(regex);
    }
}
