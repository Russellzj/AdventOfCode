import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File pageOrderFile = new File("2024/Day05/Data/OrderingRules");

        List<int[]> pageOrder = new ArrayList<>();
        Map<Integer, HashSet<Integer>> orderRules = new HashMap<>();


        try (Scanner sc = new Scanner(pageOrderFile)) {
            //
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                String[] ruleRaw = line.split("\\|");
                int beforeRule = Integer.parseInt(ruleRaw[0]);
                int afterRule = Integer.parseInt(ruleRaw[1]);

                if (orderRules.containsKey(beforeRule)) {
                    orderRules.get(beforeRule).add(afterRule);
                } else {
                    orderRules.put(beforeRule, new HashSet<>());
                    orderRules.get(beforeRule).add(afterRule);
                }
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] pagesRaw = line.split(",");
                int[] pages = new int[pagesRaw.length];
                for (int i = 0; i < pagesRaw.length; i++) {
                    pages[i] = Integer.parseInt(pagesRaw[i]);
                }
                pageOrder.add(pages);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Integer> correctRow = new ArrayList<>();

        for (int[] pages : pageOrder) {
            for (int page : pages) {
                System.out.print(page + " ");
            }
            System.out.println();
            boolean isCorrect = true;
            for (int i = 0 ; i < pages.length; i++) {

                for (int j = i - 1; j >= 0 ; j--) {
                    if (orderRules.containsKey(pages[i])) {
                        if (orderRules.get(pages[i]).contains(pages[j])) {
                            System.out.println("Value: " + pages[j] + " out of order because of " + pages[i]);
                            isCorrect = false;
                        }
                    }
                }
            }
            if (isCorrect) {
                correctRow.add(pages[pages.length / 2]);
            }

        }
        int totalRow = 0;
        for (int row : correctRow) {
            totalRow += row;
        }
        System.out.println(totalRow);
    }

}