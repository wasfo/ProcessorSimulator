import java.util.concurrent.TimeUnit;

public class Clock {
    private int currentCycle;
    private int cycleDuration = 1;
    public Clock(int currentCycle) {
        this.currentCycle = currentCycle;
    }
    public void setCycleDuration(int cycleDuration) {
        this.cycleDuration = cycleDuration;
    }
    public int getCurrentCycle() {
        return currentCycle;
    }
    public void nextCycle() throws InterruptedException {
        currentCycle++;
        //TimeUnit.SECONDS.sleep(cycleDuration);
    }
}
