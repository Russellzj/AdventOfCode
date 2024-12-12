import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    }
}