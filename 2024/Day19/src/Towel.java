import java.util.*;

public class Towel {
    private Set<String> towels = new HashSet<>();
    private Set<String> testedDesigns = new HashSet<>();
    private String regex = "()+";

    public void addTowel(String towel) {
        towels.add(towel);
        if (regex.length() != 3) {
            regex = regex.substring(0, 1) + towel + "|" + regex.substring(1);
        } else {
            regex = regex.substring(0, 1) + towel + regex.substring(1);
        }
    }

    public boolean findDesign(String pattern) {
        for (int i = 1; i <= pattern.length(); i++) {
            String testPattern = pattern.substring(0, i);
            if (towels.contains(pattern.substring(0, i))) {
                if(pattern.length() == i) {
                    return true;
                } else if (nextDesign(pattern, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean nextDesign(String pattern, int location) {
        for (int i = pattern.length(); i > location; i--) {
            String testPattern = pattern.substring(location, i);
            if (testedDesigns.contains(testPattern) || towels.contains(testPattern)) {
                if(pattern.length() == i) {
                    return true;
                } else if (nextDesign(pattern, i)) {
                    testedDesigns.add(pattern.substring(0, i));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findRegex(String pattern) {
        return pattern.matches(regex);
    }
}
