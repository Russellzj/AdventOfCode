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

        List<Integer> correctRows = new ArrayList<>();
        List<Integer> allRows = new ArrayList<>();

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
                            //System.out.println("Value: " + pages[j] + " out of order because of " + pages[i]);
                            int tempPage = pages[j];
                            pages[j] = pages[i];
                            pages[i] = tempPage;
                            i = 0;
                            isCorrect = false;
                        }
                    }
                }
            }
            if (isCorrect) {
                correctRows.add(pages[pages.length / 2]);
            } else {
                System.out.println("Row out of order");
                System.out.print("New Page Order: ");
                for (int page : pages) {
                    System.out.print(page + " ");
                }
                System.out.println();
                allRows.add(pages[pages.length / 2]);
            }


        }
        int totalCorrectRow = 0;

        for (int row : correctRows) {
            totalCorrectRow += row;
        }

        int totalAllRows = 0;
        for (int row : allRows) {
            totalAllRows += row;
        }
        System.out.println("Total middle value of correct rows: " + totalCorrectRow);
        System.out.println("Total middle values of corrected rows: " + totalAllRows);
    }

}