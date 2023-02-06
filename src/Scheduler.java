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
    public void scheduleTasks(List<Processor> availableProcessors) {
        List<Processor> assignedProcessors = new LinkedList<>();
        for (int i = 0; i < availableProcessors.size(); i++) {
            if (isQueueEmpty()) break;
            availableProcessors.get(i).executeTask(queue.poll());
            assignedProcessors.add(availableProcessors.get(i));
        }
        availableProcessors.removeAll(assignedProcessors);
    }

    class TaskComparator implements Comparator<Task> {
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
