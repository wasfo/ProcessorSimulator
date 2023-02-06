import java.util.concurrent.TimeUnit;

public class Clock {
    private int currentCycle;
    public Clock(int currentCycle) {
        this.currentCycle = currentCycle;
    }
    public int getCurrentCycle() {
        return currentCycle;
    }
    public void setCurrentCycle(int currentCycle) {
        this.currentCycle = currentCycle;
    }
    public void nextCycle() throws InterruptedException {
        currentCycle++;
      //  TimeUnit.SECONDS.sleep(1);
    }
}
