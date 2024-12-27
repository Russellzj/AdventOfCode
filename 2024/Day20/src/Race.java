import java.util.List;

public class Race {
    private char[][] track;
    private int[] start = new int[2];
    private int[] end = new int[2];
    private int[][] movements = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public Race (List<List<Character>> track) {
        this.track = new char[track.size()][track.get(0).size()];
        for (int i = 0; i < track.size(); i++) {
            for (int j = 0; j < track.get(i).size(); j++) {
                if (track.get(i).get(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                    this.track[i][j] = '.';
                }
                else if (track.get(i).get(j) == 'E') {
                    end[0] = i;
                    end[1] = j;
                    this.track[i][j] = 'E';
                }
                else {
                    this.track[i][j] = track.get(i).get(j);
                }
            }
        }
    }

    public int findPath() {
        int picoseconds = 0;
        int[] location = start;
        picoseconds = attemptPath(new int[]{0, 0}, location, picoseconds);
        return picoseconds;
    }

    private int attemptPath(int[] previousLocation, int[] location, int picoseconds) {
            char identity = track[location[0]][location[1]];
            if (identity == 'E') {
                return picoseconds;
            }
            if (identity == '#' || identity == 'S') {
                return 0;
            }
            if (identity == '.') {
                for (int[] movement : movements) {
                    int[] move = location.clone();
                    move[0] += movement[0];
                    move[1] += movement[1];
                    if (move[0] == previousLocation[0] && move[1] == previousLocation[1]) {
                        continue;
                    }
                    if (move[0] < track.length) {
                        if (move[1] < track[move[0]].length) {
                            int attempt = attemptPath(location, move, picoseconds + 1);
                            if (attempt != 0) {
                                return attempt;
                        }
                    }
                }
            }
        }else {
                System.out.println(identity + "at location " + location[0] + " " + location[1]);
            }
        return 404;
    }
}
