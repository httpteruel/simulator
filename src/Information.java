public class Information {
    private int hits = 0;
    private int misses = 0;
    private int timesRead = 0;
    private int timesWritten = 0;

    public void hit() {
        hits++;
    }

    public void miss() {
        misses++;
    }

    public void finalAcess() {
        timesRead = hits + misses;
    }

    public void write() {
        timesWritten++;
    }

    public void read() {
        timesRead++;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getTimesRead() {
        return timesRead;
    }

    public int getTimesWritten() {
        return timesWritten;
    }

    public void reset() {
        this.hits = 0;
        this.misses =  0;
        this.timesRead =  0;
        this.timesWritten = 0;   
    }
}
