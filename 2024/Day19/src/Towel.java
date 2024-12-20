import java.util.*;

public class Towel {
    private Set<String> towels = new HashSet<>();
    private Set<String> testedPatterns = new HashSet<>();

    public void addTowel(String towel) {
        towels.add(towel);
    }



    public boolean findPatter(String pattern) {
        for (int i = 1; i <= pattern.length(); i++) {
            String testPattern = pattern.substring(0, i);
            if (towels.contains(pattern.substring(0, i))) {
                if(pattern.length() == i) {
                    return true;
                } else if (findPatter(pattern.substring(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
