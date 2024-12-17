import java.util.*;

public class Robot {
    private char[][] map;
    private Map<Character, int[]> movement = new HashMap<>();
    //Current Robot Location
    private int robotX;
    private int robotY;

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
                    this.robotX = i;
                    this.robotY = j;
                }
            }
        }
    }

    public void move(char direction) {
        int possibleX = robotX + movement.get(direction)[0];
        int possibleY = robotY + movement.get(direction)[1];
        //If the spot is empty move to new spot
        if (map[possibleX][possibleY] == '.') {
            map[robotX][robotY] = '.';
            map[possibleX][possibleY] = '@';
            this.robotX = possibleX;
            this.robotY = possibleY;
        } else if (map[possibleX][possibleY] == 'O') {
            if (moveBox(direction, possibleX, possibleY)) {
                map[robotX][robotY] = '.';
                map[possibleX][possibleY] = '@';
                this.robotX = possibleX;
                this.robotY = possibleY;
            }
        }
    }

    private boolean moveBox(char direction, int x, int y) {
        int boxX = x + movement.get(direction)[0];
        int boxY = y + movement.get(direction)[1];
        if (map[boxX][boxY] == '.') {
            map[boxX][boxY] = 'O';
            return true;
        }
        if (map[boxX][boxY] == 'O') {
            if (moveBox(direction, boxX, boxY)) {
                map[boxX][boxY] = 'O';
                return true;
            }
        }
        return false;
    }

    public void printMap() {
        for (char[] row : map) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getBoxGPS() {
        int GPS = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'O') {
                    GPS += i * 100 + j;
                }
            }
        }
        return GPS;
    }
}
