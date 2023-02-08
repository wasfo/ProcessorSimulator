import java.util.Objects;

public class Processor {
    private Task currentTask;
    private boolean isAvailable;
    private int timeNeededToBeAvailable;
    private final long processorID;

    public Processor(boolean isAvailable, long processorID) {
        this.isAvailable = isAvailable;
        currentTask = null;
        this.processorID = processorID;
    }

    public void executeTask(Task task) {
        timeNeededToBeAvailable = task.getExecutionTime();
        currentTask = task;
        setAvailable(false);
    }
    public void update() {
        if (!isAvailable())
            timeNeededToBeAvailable--;
        if (timeNeededToBeAvailable == 0) {
            setAvailable(true);
            if (currentTask != null) {
                currentTask = null;
            }
        }
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Processor " + processorID + " is executing " + currentTask + " / time needed = " + timeNeededToBeAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return processorID == processor.processorID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(processorID);
    }
}
