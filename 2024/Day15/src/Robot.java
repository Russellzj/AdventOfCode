import java.util.*;

public class Robot {
    private char[][] map;
    private Map<Character, int[]> movement = new HashMap<>();
    private int positionX;
    private int positionY;

    {
        movement.put('^', new int[]{-1, 0});
        movement.put('v', new int[]{1, 0});
        movement.put('<', new int[]{0, -1});
        movement.put('>', new int[]{0, 1});
    }


    public Robot(List<List<Character>>  map) {
        this.map = new char[map.size()][map.getFirst().size()];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                this.map[i][j] = map.get(i).get(j);
                if(map.get(i).get(j) == '@') {
                    positionX = i;
                    positionY = j;
                }
            }
        }
    }

    public void move(char direction) {

    }
}
