import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Reads in file and adds data to ArrayList
        File locations = new File("2024//Day02//Data//RedReactor");
        List<List<Integer>> redReactor = new ArrayList<>();
        try (Scanner sc = new Scanner(locations)){
            while (sc.hasNextLine()) {
                List<Integer> report = new ArrayList<>();
                String[] values = sc.nextLine().split(" ");
                for (String value : values) {
                    report.add(Integer.parseInt(value));
                }
                redReactor.add(report);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Total Safe Reports
        int safe = 0;
        int safeRemoved = 0;
        for (List<Integer> report : redReactor) {
            if (isSafe(report)) {
                safe++;
            }
            if (isSafeRemovableHard(report)) {
                safeRemoved++;
            }
        }
        System.out.println("Number of safe reports: " + safe);
        System.out.println("Number of safe reports with removable value: " + safeRemoved);
    }

    //Tests if the values located in the report are safe or unsafe
    public static boolean isSafe(List<Integer> report) {
        boolean increase = report.get(0) < report.get(1);
        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) == report.get(i - 1)) {
                return false;
            }
            if (Math.abs(report.get(i) - report.get(i - 1)) > 3) {
                return false;
            }
            if (increase ^ report.get(i) > report.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    //Same as isSafe except we are allowed to remove one of the elements
    public static boolean isSafeRemovableHard(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> tempReport = new ArrayList<>(report);
            tempReport.remove(i);
            if (isSafe(tempReport)) {
                return true;
            }
        }
        return false;
    }

}