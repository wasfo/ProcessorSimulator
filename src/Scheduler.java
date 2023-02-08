import java.util.*;

public class Scheduler {
    private PriorityQueue<Task> queue;
    public PriorityQueue<Task> getQueue() {
        return queue;
    }
    public Scheduler() {
        queue = new PriorityQueue<>(new TaskComparator());
    }
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
    public void addTask(Task task) {
        queue.add(task);
    }
    public void scheduleTasks(Set<Processor> availableProcessors, Set<Processor> busyProcessor) {
        for (Processor availableProcessor : availableProcessors) {
            if (isQueueEmpty()) break;
            availableProcessor.executeTask(queue.poll());
            busyProcessor.add(availableProcessor);
        }
        availableProcessors.removeAll(busyProcessor);
    }
    static class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task taskOne, Task taskTwo) {
            if (taskOne.getPriority() > taskTwo.getPriority()) return -1;
            if (taskOne.getPriority() == taskTwo.getPriority()) {
                if (taskOne.getExecutionTime() > taskTwo.getExecutionTime()) return -1;
                if (taskOne.getExecutionTime() == taskTwo.getExecutionTime()) return Math.random() < 0.5 ? 1 : -1;
                return 1;
            }
            return 1;
        }
    }
}
