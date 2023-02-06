public class Processor {
    private Task currentTask;
    private boolean isAvailable;
    private int timeNeededToBeAvailable;
    private long processorID;
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

    public long getProcessorID() {
        return processorID;
    }

    public void setProcessorID(long processorID) {
        this.processorID = processorID;
    }
    public void update() {
        if (isAvailable() == false) {
            timeNeededToBeAvailable--;
        }
        if (timeNeededToBeAvailable == 0) {
            setAvailable(true);
            if (currentTask != null) {
                System.out.println("Processor " + processorID + " Done executing task " + currentTask);
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
        return "Processor " + processorID + " is executing " + currentTask  + " / time needed = " + timeNeededToBeAvailable;
    }
}
