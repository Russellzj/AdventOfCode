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
        System.out.println("All Multiply Total: " + multiplyCorruptData(corrupt.toString()));
        System.out.println("Do Multiply Total: " + multiplyCorruptData(findDoCommands(corrupt.toString())));
    }

    public static int multiplyCorruptData(String corrupt) {
        int total = 0;
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(corrupt);
        while (matcher.find()) {
            String numbericalValues = matcher.group(0).substring(4, matcher.group(0).length() - 1);
            String[] numericalValue = numbericalValues.split(",");
            total += Integer.parseInt(numericalValue[0]) * Integer.parseInt(numericalValue[1]);
        }
        return total;
    }

    public static String findDoCommands(String corrupt) {
        StringBuilder doCommands = new StringBuilder();
        int stringPosition = 0;
        int nextDo = 0;

        while (true) {
            nextDo = corrupt.indexOf("do");
            if (nextDo == -1) {
                break;
            }
            String nextDoString = corrupt.substring(0, nextDo);
            corrupt = corrupt.substring(nextDo + 2);
            if (!nextDoString.matches("n't.*")) {
                doCommands.append(nextDoString);
            }
        }
        return doCommands.toString();
    }
}