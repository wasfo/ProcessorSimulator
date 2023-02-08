import java.util.Comparator;

public class Task {
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private final String taskID;

    public Task(int creationTime, int executionTime, int priority, String taskID) {
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.taskID = taskID;
    }

    public String getTaskID() {return taskID;}
    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return  taskID + ": " + creationTime + " " + executionTime + " " + priority;
    }
}
