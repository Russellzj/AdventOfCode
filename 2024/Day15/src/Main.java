import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File mapAndMovements = new File("2024/Day15/Data/MapAndMovements");
        List<List<Character>> map = new ArrayList<>();
        List<Character> movements = new ArrayList<>();
        try (Scanner sc = new Scanner(mapAndMovements)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                List<Character> mapRow = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    mapRow.add(c);
                }
                map.add(mapRow);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (char c : line.toCharArray()) {
                    movements.add(c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Robot robot = new Robot(map);
        for (char movement : movements) {
            robot.move(movement);
        }
        System.out.println("Box GPS: " + robot.getBoxGPS());



        System.out.println("END");
    }
}