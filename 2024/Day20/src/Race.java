import java.util.List;

public class Race {
    private Track[][] track;
    private int[] start = new int[2];
    private int[] end = new int[2];
    private int[][] movements = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public Race (List<List<Character>> track) {
        this.track = new Track[track.size()][track.get(0).size()];
        for (int i = 0; i < track.size(); i++) {
            for (int j = 0; j < track.get(i).size(); j++) {
                this.track[i][j] = new Track();
                if (track.get(i).get(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                    this.track[i][j].setStart(true);
                }
                else if (track.get(i).get(j) == 'E') {
                    end[0] = i;
                    end[1] = j;
                    this.track[i][j].setEnd(true);
                }
                else if (i == 0 || i == track.size() - 1 || j == 0 || j == track.get(i).size() - 1) {
                    this.track[i][j].setBarrier(true);
                }
                else if (track.get(i).get(j) == '#') {
                    this.track[i][j].setWall(true);
                }
                else if (track.get(i).get(j) == '.') {
                    this.track[i][j].setFree(true);
                }
            }
        }
    }

    public int findPath() {
        attemptPath(start, 0);
        return track[end[0]][end[1]].getPicoSecond();
    }

    private void attemptPath(int[] location, int picoseconds) {
        Track identity = track[location[0]][location[1]];
            if (identity.isEnd()) {
                track[location[0]][location[1]].setPicoSecond(picoseconds);
                return;
            }
            if (identity.isBarrier() || identity.isWall()) {
                return;
            }
            if (identity.isFree() || identity.isStart()) {
                for (int[] movement : movements) {
                    int[] move = location.clone();
                    move[0] += movement[0];
                    move[1] += movement[1];
                    if (track[move[0]][move[1]].isVisited()) {
                        continue;
                    }
                    if (move[0] < track.length) {
                        if (move[1] < track[move[0]].length) {
                            track[location[0]][location[1]].setPicoSecond(picoseconds);
                            track[location[0]][location[1]].setVisited(true);
                            attemptPath(move, picoseconds + 1);
                    }
                }
            }
        }
    }
}
