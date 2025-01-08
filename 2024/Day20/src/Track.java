public class Track {
    private int picoSecond;
    private boolean wall = false;
    private boolean visited = false;
    private boolean free = false;
    private boolean end = false;
    private boolean start = false;
    private boolean barrier = false;

    public void setPicoSecond(int picoSecond) {
        this.picoSecond = picoSecond;
    }

    public int getPicoSecond() {
        return picoSecond;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }
    public boolean isWall() {
        return wall;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setFree(boolean isFree) {
        this.free = isFree;
    }
    public boolean isFree() {
        return free;
    }

    public void setEnd(boolean isEnd) {
        this.end = isEnd;
    }

    public boolean isEnd() {
        return end;
    }

    public void setStart(boolean isStart) {
        this.start = isStart;
    }

    public boolean isStart() {
        return start;
    }

    public void setBarrier(boolean isBarrier) {
        this.barrier = isBarrier;
    }

    public boolean isBarrier() {
        return barrier;
    }
}
