import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargeRobot {
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

    public LargeRobot(List<List<Character>>  map) {
        this.map = new char[map.size()][map.getFirst().size()*2];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == '#') {
                    this.map[i][j*2] = '#';
                    this.map[i][j*2+1] = '#';
                }
                else if (map.get(i).get(j) == '.') {
                    this.map[i][j*2] = '.';
                    this.map[i][j*2+1] = '.';
                }
                else if (map.get(i).get(j) == 'O') {
                    this.map[i][j*2] = '[';
                    this.map[i][j*2+1] = ']';
                }
                else if (map.get(i).get(j) == '@') {
                    this.map[i][j*2] = '@';
                    this.robotX = i;
                    this.robotY = j * 2;
                    this.map[i][j*2+1] = '.';
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
        } else if (map[possibleX][possibleY] == '[' || map[possibleX][possibleY] == ']') {
            if (moveBox(direction, possibleX, possibleY)) {
                map[robotX][robotY] = '.';
                map[possibleX][possibleY] = '@';
                this.robotX = possibleX;
                this.robotY = possibleY;
            }
        }
    }

    private boolean moveBox(char direction, int x, int y) {
        int boxLeft;
        int boxRight;
        if (map[x][y] == '[') {
            boxLeft = y;
            boxRight = y + 1;
        } else if (map[x][y] == ']') {
            boxLeft = y - 1;
            boxRight = y;
        } else {
            System.out.print("ERROR: ");
            return true;

        }
        //Left or right box movement
        if (direction == '<' || direction == '>') {
            int possibleY = y + movement.get(direction)[1] * 2;
            if (map[x][possibleY] == '.') {
                map[x][boxLeft + movement.get(direction)[1]] = '[';
                map[x][boxRight + movement.get(direction)[1]] = ']';
                return true;
            } else if (map[x][possibleY] == '[' || map[x][possibleY] == ']') {
                if (moveBox(direction, x, possibleY)) {
                    map[x][boxLeft + movement.get(direction)[1]] = '[';
                    map[x][boxRight + movement.get(direction)[1]] = ']';
                    return true;
                }
            }
        //Up or Down box movement
        } else if (direction == '^' || direction == 'v') {
            int possibleX = x + movement.get(direction)[0];

            //Checks to see if the box can move in the direction even if a box is already there
            boolean boxLeftBlock = map[possibleX][boxLeft] == '[' || map[possibleX][boxLeft] == ']';
            if (boxLeftBlock) {
                boxLeftBlock = canBoxMove(direction, possibleX, boxLeft);
            }
            boolean boxRightBlock = map[possibleX][boxRight] == '[' || map[possibleX][boxRight] == ']';
            if (boxRightBlock) {
                boxRightBlock = canBoxMove(direction, possibleX, boxRight);
            }


            if ((map[possibleX][boxLeft] == '.' || boxLeftBlock) && (map[possibleX][boxRight] == '.' || boxRightBlock)) {
                if (boxLeftBlock) {
                    moveBox(direction, possibleX, boxLeft);
                }
                if (boxRightBlock) {
                    if (map[possibleX][boxRight] == '[') {
                        moveBox(direction, possibleX, boxRight);
                    }
                }
                map[possibleX][boxLeft] = '[';
                map[possibleX][boxRight] = ']';
                map[x][boxLeft] = '.';
                map[x][boxRight] = '.';
                return true;
            }
        }
        return false;
    }

    private boolean canBoxMove(char direction, int x, int y) {
        int boxLeft;
        int boxRight;
        if (map[x][y] == '[') {
            boxLeft = y;
            boxRight = y + 1;
        } else if (map[x][y] == ']') {
            boxLeft = y - 1;
            boxRight = y;
        } else {
            System.out.println("ERROR");
            return true;
        }

        if (direction == '^' || direction == 'v') {
            int possibleX = x + movement.get(direction)[0];
            boolean boxLeftBlock = map[possibleX][boxLeft] == '[' || map[possibleX][boxLeft] == ']';
            if (boxLeftBlock) {
                boxLeftBlock = canBoxMove(direction, possibleX, boxLeft);
            }
            boolean boxRightBlock = map[possibleX][boxRight] == '[' || map[possibleX][boxRight] == ']';
            if (boxRightBlock) {
                boxRightBlock = canBoxMove(direction, possibleX, boxRight);
            }
            if ((map[possibleX][boxLeft] == '.' || boxLeftBlock) && (map[possibleX][boxRight] == '.' || boxRightBlock)) {
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
                if (map[i][j] == '[') {
                    GPS += i * 100 + j;
                }
            }
        }
        return GPS;
    }
}
