import java.util.List;

public class StringPatternMatcher {

    public static long countWaysToCreateString(String pattern, List<String> towels) {
        int n = pattern.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;  // There's one way to create the empty string

        // Iterate through each position in the string
        for (int i = 1; i <= n; i++) {
            // Check each pattern to see if it can match the substring ending at position i
            for (String towel : towels) {
                int patternLength = towel.length();
                if (i >= patternLength && pattern.substring(i - patternLength, i).equals(towel)) {

                    dp[i] += dp[i - patternLength];

                }
            }
        }
        for (long d : dp) {
            System.out.println(d);
        }
        return dp[n];  // The number of ways to create the full string
    }
}


