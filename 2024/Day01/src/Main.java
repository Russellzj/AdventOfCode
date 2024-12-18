import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File locations = new File("2024//Day01//Data//Locations.txt");
        //Following holds location data from file
        List<Integer> locationsA  = new ArrayList<>();
        List<Integer> locationsB = new ArrayList<>();
        try(Scanner sc = new Scanner(locations)) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] twoLocations = line.split("   ");
                locationsA.add(Integer.parseInt(twoLocations[0]));
                locationsB.add(Integer.parseInt(twoLocations[1]));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(findDifferencesOfLowestValues(locationsA, locationsB));
        System.out.println(findSimilarityScore(locationsA, locationsB));
    }

    static int findDifferencesOfLowestValues(List<Integer> locationsA, List<Integer> locationsB) {
        int differenceBetweenLocationAAndB = 0;
        Collections.sort(locationsA);
        Collections.sort(locationsB);
        for (int i = 0; i < locationsA.size(); i++) {
            differenceBetweenLocationAAndB += Math.abs(locationsA.get(i) - locationsB.get(i));
        }
        return differenceBetweenLocationAAndB;
    }

    static int findSimilarityScore(List<Integer> locationsA, List<Integer> locationsB) {
        int similarityScore = 0;
        for (int locationA : locationsA) {
            int timesValueInB = 0;
            for (int locationB : locationsB) {
                if (locationA == locationB) {
                    timesValueInB++;
                }
            }
            similarityScore += timesValueInB * locationA;
        }
        return similarityScore;
    }
}